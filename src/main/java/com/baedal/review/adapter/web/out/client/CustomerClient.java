package com.baedal.review.adapter.web.out.client;

import com.baedal.review.adapter.web.out.response.GetCustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${services.customer.url}")
public interface CustomerClient {

  @GetMapping("/v0/reviewInfo/{customerId}")
  GetCustomerResponse getCustomer(@PathVariable("customerId") Long customerId);
}
