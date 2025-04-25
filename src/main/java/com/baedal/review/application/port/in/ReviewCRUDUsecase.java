package com.baedal.review.application.port.in;

import com.baedal.review.adapter.persistence.entity.ReviewScore;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.dto.StoreReviewSummary;
import java.util.List;

public interface ReviewCRUDUsecase {

  ReviewDetail findReviewDetail(Long reviewId);

  void deleteReview(Long reviewId);

  Long createReview(
      Long customerId,
      Long storeId,
      Long orderId,
      ReviewScore score,
      String content,
      List<String> attachments);

  List<StoreReviewSummary> findTop10ReviewSummary(Long storeId);
}
