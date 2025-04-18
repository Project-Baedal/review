package com.baedal.review.application.service;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.repository.ReviewRepository;
import com.baedal.review.adapter.web.in.mapper.ReviewDetailMapper;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.in.ReviewCRUDUsecase;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.application.port.out.StorePort;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCRUDService implements ReviewCRUDUsecase {

  private final ReviewDetailMapper reviewDetailMapper;

  private final CustomerPort customerPort;

  private final StorePort storePort;

  private final ReviewRepository reviewRepository;

//  private final ReviewQueryPort reviewQueryPort;

  // FIXME: 비동기 NIO 쓸 수 있으면 쓰자.
  @Transactional(readOnly = true)
  public ReviewDetail findReviewDetail(Long reviewId) {
    // FIXME: 연관 관계 조회 최적화.
    ReviewAggregate reviewAggregate = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new RuntimeException(""));

    Customer customer = customerPort.getCustomer(reviewAggregate.getReviewer().getCustomerId());

    Store store = storePort.getStore(reviewAggregate.getStoreId());

    return reviewDetailMapper.toReviewDetail(reviewAggregate, customer, store);
  }

  @Override
  public void deleteReview(Long reviewId) {
    // TODO: 존재하는지 검증할까? 예외처리.
    //  - 예외로 충분할수도.
    reviewRepository.delete(new ReviewAggregate(reviewId));
  }
}
