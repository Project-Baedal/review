package com.baedal.review.adapter.persistence.projection;

import com.baedal.review.adapter.persistence.entity.ReviewScore;

// TODO: 이거를 App 계층의 DTO로 보낼지?
//  - App 계층에서 필요한 형태로 조회 프로젝션 하면 뭐 adapter에서 응용계층의 의존도니깐 괜찮을지도.
public interface ReviewSummaryProjection {

  Long getId();

  Long getReviewerId();

  ReviewScore getScore();

  String getContent();
}
