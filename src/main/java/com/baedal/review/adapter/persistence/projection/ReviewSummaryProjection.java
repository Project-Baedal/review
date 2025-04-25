package com.baedal.review.adapter.persistence.projection;

import com.baedal.review.adapter.persistence.entity.ReviewScore;

public interface ReviewSummaryProjection {

  Long getId();

  Long getReviewerId();

  ReviewScore getScore();

  String getContent();
}
