package com.baedal.review.application.service;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.application.mapper.ReviewDetailMapper;
import com.baedal.review.application.mapper.ReviewSummaryMapper;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.dto.StoreReviewSummary;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.application.port.out.ReviewQueryPort;
import com.baedal.review.domain.model.Customer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

  private final ReviewDetailMapper reviewDetailMapper;

  private final ReviewSummaryMapper reviewSummaryMapper;

  private final CustomerPort customerPort;

  private final ReviewQueryPort reviewQueryPort;

  // FIXME: 비동기 NIO 쓸 수 있으면 쓰자.
  @Transactional(readOnly = true)
  public ReviewDetail findReviewDetail(Long reviewId) {
    // FIXME: 연관 관계 조회 최적화.
    ReviewAggregate reviewAggregate = reviewQueryPort.findById(reviewId);

    Customer customer = customerPort.getCustomer(reviewAggregate.getReviewerId());

    return reviewDetailMapper.toReviewDetail(reviewAggregate, customer);
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

  @Transactional(readOnly = true)
  // TODO: Cache this result
  public Double findAverageScoreOfStore(Long storeId) {
    return reviewQueryPort.calculateAverageScore(storeId);
  }
}
