package com.baedal.review.adapter.web.in.request;

import com.baedal.review.adapter.persistence.entity.ReviewScore;
import java.util.List;

public record CreateReviewRequest(
    Long customerId, // TODO: customerId는 Security ContextHolder에서 가져오기로.
    Long storeId,
    Long orderId,
    ReviewScore score,
    String content,
    List<String> attachments) {

}
