package com.baedal.review.adapter.web.mapper;

import com.baedal.review.adapter.web.in.request.CreateReviewRequest;
import com.baedal.review.adapter.web.in.response.GetAverageScoreResponse;
import com.baedal.review.application.port.dto.CreateReviewCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebReviewMapper {

  CreateReviewCommand.Request toCommand(CreateReviewRequest r);

  GetAverageScoreResponse toResponse(Double average);
}
