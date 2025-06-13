package com.baedal.review.application.port.dto;

import com.baedal.review.domain.model.ReviewScore;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReviewCommand {

  @Getter
  @Builder
  public static class Request {

    private Long customerId;
    private Long storeId;
    private Long orderId;
    private ReviewScore score;
    private String content;
    private List<String> attachments;
  }
}
