package com.baedal.review.adapter.web.in.response;

import com.baedal.review.application.port.dto.StoreReviewSummary;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStoreTop10ReviewsResponse {

  private List<StoreReviewSummary> data;
}
