package com.baedal.review.application.port.dto;

import com.baedal.review.adapter.persistence.entity.ReviewScore;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDetail {

  private Long reviewId;

  private String content;

  private ReviewScore reviewScore;

  private LocalDateTime createdAt;

  private ReviewerInfo reviewer;

  private StoreInfo store;

  private List<AttachmentInfo> attachments;

  @Getter
  @AllArgsConstructor
  public static class ReviewerInfo {

    private Long reviewerId;
    private String nickname;
  }

  @Getter
  @AllArgsConstructor
  public static class StoreInfo {

    private Long storeId;
    private String name;
  }

  @Getter
  @AllArgsConstructor
  public static class AttachmentInfo {

    private Long attachmentId;
    private String url;
  }
}
