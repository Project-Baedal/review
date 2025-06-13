package com.baedal.review.adapter.web.mapper;

import com.baedal.review.adapter.web.in.request.CreateReviewRequest;
import com.baedal.review.adapter.web.in.response.GetAverageScoreResponse;
import com.baedal.review.adapter.web.in.response.GetStoreTop10ReviewsResponse;
import com.baedal.review.application.port.dto.CreateReviewCommand;
import com.baedal.review.application.port.dto.StoreReviewSummary;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebReviewMapper {

  CreateReviewCommand.Request toCommand(CreateReviewRequest r);

  GetAverageScoreResponse toResponse(Double average);

  default GetStoreTop10ReviewsResponse toResponse(List<StoreReviewSummary> data) {
    return GetStoreTop10ReviewsResponse.builder()
        .data(data)
        .build();
  }
}
