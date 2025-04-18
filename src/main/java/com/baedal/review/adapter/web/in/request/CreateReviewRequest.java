package com.baedal.review.adapter.web.in.request;

import com.baedal.review.adapter.persistence.entity.ReviewScore;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateReviewRequest {

  // TODO: customerId는 Security ContextHolder에서 가져오기로.
  private final Long customerId;

  private final Long storeId;

  private final Long orderId;

  private final ReviewScore score;

  private final String content;

  private final List<String> attchments;
}
