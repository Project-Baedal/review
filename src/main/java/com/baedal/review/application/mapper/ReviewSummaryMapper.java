package com.baedal.review.application.mapper;

import com.baedal.review.application.port.dto.StoreReviewSummary;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.ReviewSummary;
import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewSummaryMapper {

  @Mapping(target = "id", source = "review.id")
  @Mapping(target = "reviewScore", source = "review.score")
  @Mapping(target = "content", source = "review.content")
  @Mapping(target = "reviewer.customerId", source = "customer.id")
  @Mapping(target = "reviewer.name", source = "customer.name")
  StoreReviewSummary toReviewSummary(ReviewSummary review, Customer customer);

  default List<StoreReviewSummary> toReviewSummaryList(
      List<ReviewSummary> reviews,
      Map<Long, Customer> customers) {
    return reviews.stream()
        .map(review -> toReviewSummary(review, customers.get(review.getReviewerId())))
        .toList();
  }
}
