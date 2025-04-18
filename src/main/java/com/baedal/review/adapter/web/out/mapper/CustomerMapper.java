package com.baedal.review.adapter.web.out.mapper;

import com.baedal.review.adapter.web.out.response.GetCustomerResponse;
import com.baedal.review.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  Customer toReviewer(GetCustomerResponse response);
}
