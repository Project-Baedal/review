package com.baedal.review.adapter.web.out.response;

import lombok.Builder;

@Builder
public record GetCustomerResponse(Long customerId, String name) {

}
