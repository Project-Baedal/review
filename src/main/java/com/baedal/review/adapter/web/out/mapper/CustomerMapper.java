package com.baedal.review.adapter.web.out.mapper;

import com.baedal.review.adapter.web.out.response.GetCustomerResponse;
import com.baedal.review.adapter.web.out.response.GetCustomersResponse;
import com.baedal.review.domain.model.Customer;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  @Mapping(source = "customerId", target = "id")
  Customer toReviewer(GetCustomerResponse response);

  @Mapping(source = "customerId", target = "id")
  Collection<Customer> toReviewers(Collection<GetCustomerResponse> res);

  default Collection<Customer> toReviewers(GetCustomersResponse response) {
    return toReviewers(response.getData());
  }
}
