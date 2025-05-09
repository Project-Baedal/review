package com.baedal.review.application.mapper;

import com.baedal.review.adapter.persistence.projection.ReviewSummaryProjection;
import com.baedal.review.application.port.dto.StoreReviewSummary;
import com.baedal.review.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewSummaryMapper {

  @Mapping(target = "id", source = "projection.id")
  @Mapping(target = "reviewScore", source = "projection.score")
  @Mapping(target = "content", source = "projection.content")
  @Mapping(target = "reviewer.customerId", source = "customer.id")
  @Mapping(target = "reviewer.name", source = "customer.name")
  StoreReviewSummary toDto(ReviewSummaryProjection projection, Customer customer);
}
