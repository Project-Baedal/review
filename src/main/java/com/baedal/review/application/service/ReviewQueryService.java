package com.baedal.review.application.service;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.application.mapper.ReviewDetailMapper;
import com.baedal.review.application.mapper.ReviewSummaryMapper;
import com.baedal.review.application.port.dto.PagedResponse;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.dto.StoreReviewSummary;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.application.port.out.ReviewQueryPort;
import com.baedal.review.domain.model.Customer;
import java.util.List;
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
  public PagedResponse<ReviewDetail> findReviewDetailsPage(
      Long storeId, Integer number, Integer size) {
    Slice<ReviewAggregate> slice = reviewQueryPort.findByStoreId(storeId, number, size);

    List<ReviewDetail> data = slice
        .stream().parallel()
        .map(review -> {
          Customer customer = customerPort.getCustomer(review.getReviewerId());
          return reviewDetailMapper.toReviewDetail(review, customer);
        })
        .toList();

    return reviewDetailMapper.toPagedResponse(
        data,
        slice.hasNext(),
        slice.getNumber(),
        slice.getSize());
  }

  @Transactional(readOnly = true)
  public List<StoreReviewSummary> findTop10ReviewSummary(Long storeId) {
    return reviewQueryPort.findTop10ReviewOfStore(storeId)
        .parallelStream()
        .map(proj -> {
          Customer customer = customerPort.getCustomer(proj.getReviewerId());
          return reviewSummaryMapper.toDto(proj, customer);
        })
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
