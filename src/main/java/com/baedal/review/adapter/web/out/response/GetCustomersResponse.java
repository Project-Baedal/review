package com.baedal.review.adapter.web.out.response;

import java.util.Collection;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCustomersResponse {

  Collection<GetCustomerResponse> data;
}
