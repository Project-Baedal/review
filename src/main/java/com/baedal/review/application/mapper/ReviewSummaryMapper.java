package com.baedal.review.application.mapper;

import com.baedal.review.application.port.dto.StoreReviewSummary;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.ReviewSummary;
import java.util.Collection;
import java.util.HashMap;
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
      Collection<Customer> customers) {
    Map<Long, Customer> customerMap = makeCustomerMap(customers);
    return reviews.stream()
        .map(review -> toReviewSummary(review, customerMap.get(review.getReviewerId())))
        .toList();
  }

  private Map<Long, Customer> makeCustomerMap(Collection<Customer> customers) {
    HashMap<Long, Customer> customerMap = new HashMap<>();
    for (Customer customer : customers) {
      customerMap.put(customer.getId(), customer);
    }
    return customerMap;
  }

}
