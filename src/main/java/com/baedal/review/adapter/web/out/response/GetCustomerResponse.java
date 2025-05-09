package com.baedal.review.adapter.web.out.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCustomerResponse {

  private Long customerId;
  private String name;
}
