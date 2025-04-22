package com.baedal.review.application.port.dto;

import com.baedal.review.adapter.persistence.entity.ReviewScore;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewDetail {

  private final Long reviewId;

  private final String content;

  private final ReviewScore reviewScore;

  private final LocalDateTime createdAt;

  private final ReviewerInfo reviewer;

  private final StoreInfo store;

  private final List<AttachmentInfo> attachments;

  @Getter
  @Builder
  @AllArgsConstructor
  public static class ReviewerInfo {

    private final Long customerId;
    private final String name;
  }

  @Getter
  @Builder
  @AllArgsConstructor
  public static class StoreInfo {

    private final Long storeId;
    private final String name;
  }

  @Getter
  @Builder
  @AllArgsConstructor
  public static class AttachmentInfo {

    private final Long attachmentId;
    private final String url;
  }
}
