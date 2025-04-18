package com.baedal.review.application.port.in;

import com.baedal.review.application.port.dto.ReviewDetail;

public interface ReviewCRUDUsecase {

  ReviewDetail findReviewDetail(Long reviewId);

  void deleteReview(Long reviewId);
}
