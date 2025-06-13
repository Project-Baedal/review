package com.baedal.review.adapter.persistence.projection;

import com.baedal.review.domain.model.ReviewScore;

public interface ReviewSummaryProjection extends Comparable<ReviewSummaryProjection> {

  Long getId();

  Long getReviewerId();

  ReviewScore getScore();

  String getContent();

  default int compareTo(ReviewSummaryProjection o) {
    if (o == null) {
      return 1;
    }
    return o.getId().compareTo(this.getId());
  }
}
