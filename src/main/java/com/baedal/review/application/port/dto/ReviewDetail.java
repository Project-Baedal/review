package com.baedal.review.application.port.dto;

import com.baedal.review.adapter.persistence.entity.ReviewScore;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDetail implements Comparable<ReviewDetail> {

  private final Long reviewId;

  private final String content;

  private final ReviewScore reviewScore;

  private final LocalDateTime createdAt;

  private final ReviewerInfo reviewer;

  private final List<AttachmentInfo> attachments;

  @Getter
  @Builder
  public static class ReviewerInfo {

    private final Long customerId;

    private final String name;
  }

  @Getter
  @Builder
  public static class AttachmentInfo {

    private final Long attachmentId;

    private final String url;
  }

  @Override
  public int compareTo(ReviewDetail o) {
    if (o == null) {
      return 1;
    }
    return o.getReviewId().compareTo(this.getReviewId());
  }
}
