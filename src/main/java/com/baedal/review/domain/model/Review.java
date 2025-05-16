package com.baedal.review.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Review {

  private Long id;

  private Long reviewerId;

  private Long storeId;

  private Long orderId;

  private ReviewScore score;

  private LocalDateTime createdAt;

  private String content;

  private List<ReviewAttachment> attachments;

  @Getter
  @Builder
  public static class ReviewAttachment {

    private Long id;

    private String url;
  }
}
