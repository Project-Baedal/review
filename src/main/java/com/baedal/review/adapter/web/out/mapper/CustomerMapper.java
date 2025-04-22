package com.baedal.review.adapter.web.out.mapper;

import com.baedal.review.adapter.web.out.response.GetCustomerResponse;
import com.baedal.review.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  @Mapping(source = "customerId", target = "id")
  Customer toReviewer(GetCustomerResponse response);
}
