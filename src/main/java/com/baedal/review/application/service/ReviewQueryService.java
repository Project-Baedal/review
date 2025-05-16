package com.baedal.review.application.service;

import com.baedal.review.application.mapper.ReviewDetailMapper;
import com.baedal.review.application.mapper.ReviewSummaryMapper;
import com.baedal.review.application.port.dto.PagedResponse;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.dto.StoreReviewSummary;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.application.port.out.ReviewQueryPort;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.DomainSlice;
import com.baedal.review.domain.model.Review;
import com.baedal.review.domain.model.ReviewSummary;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

  private final ReviewDetailMapper reviewDetailMapper;

  private final ReviewSummaryMapper reviewSummaryMapper;

  private final CustomerPort customerPort;

  private final ReviewQueryPort reviewQueryPort;

  @Transactional(readOnly = true)
  public PagedResponse<ReviewDetail> findReviewDetailsByStore(
      Long storeId, Integer number, Integer size) {
    DomainSlice<Review> slice = reviewQueryPort.findByStoreId(storeId, number, size);

    // Fetch Customers' IDs
    List<Long> customerIds = slice.stream().parallel()
        .map(Review::getReviewerId)
        .toList();

    // Mapping id-customer
    Map<Long, Customer> customerMap = makeCustomerMap(customerIds);

    // Entity to DTO with Map
    List<ReviewDetail> data = slice.stream().parallel()
        .map(r -> reviewDetailMapper.toReviewDetail(
            r,
            customerMap.get(r.getReviewerId())))
        .sorted()
        .toList();

    return reviewDetailMapper.toPagedResponse(
        data,
        slice.hasNext(),
        slice.getNumber(),
        slice.getSize());
  }

  private Map<Long, Customer> makeCustomerMap(List<Long> customerIds) {
    Collection<Customer> customers = customerPort.getCustomersByIds(customerIds);

    HashMap<Long, Customer> customerMap = new HashMap<>();
    for (Customer customer : customers) {
      customerMap.put(customer.getId(), customer);
    }
    return customerMap;
  }

  @Transactional(readOnly = true)
  public List<StoreReviewSummary> findTop10ReviewSummary(Long storeId) {
    List<ReviewSummary> reviewSummaries = reviewQueryPort.findTop10ReviewOfStore(storeId);

    // Fetch Customers' IDs
    List<Long> customerIds = reviewSummaries.stream().parallel()
        .map(ReviewSummary::getReviewerId)
        .toList();

    // Mapping id-customer
    Map<Long, Customer> customerMap = makeCustomerMap(customerIds);

    return reviewSummaries.parallelStream()
        .map(review -> reviewSummaryMapper.toDto(
            review,
            customerMap.get(review.getReviewerId()))
        )
        .sorted()
        .toList();
  }

  @Cacheable(
      cacheNames = "storeAverageScore",
      key = "#storeId",
      unless = "#result==null"
  )
  @Transactional(readOnly = true)
  public Double findAverageScoreOfStore(Long storeId) {
    return reviewQueryPort.calculateAverageScore(storeId);
  }
}
