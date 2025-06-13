package com.baedal.review.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewSummary implements Comparable<ReviewSummary>{

  private final Long id;

  private final Long reviewerId;

  private final ReviewScore score;

  private final String content;

  @Override
  public int compareTo(ReviewSummary o) {
    if (o == null) {
      return 1;
    }
    return o.getId().compareTo(this.getId());
  }
}
