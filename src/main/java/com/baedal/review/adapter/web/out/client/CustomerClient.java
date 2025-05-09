package com.baedal.review.adapter.web.out.client;

import com.baedal.review.adapter.web.out.response.GetCustomerResponse;
import com.baedal.review.adapter.web.out.response.GetCustomersResponse;
import java.util.Collection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-service", url = "${services.customer.url}")
public interface CustomerClient {

  @GetMapping("/v0/reviewInfos/{customerId}")
  GetCustomerResponse getCustomer(@PathVariable("customerId") Long customerId);

  @GetMapping("/v0/reviewInfos")
  GetCustomersResponse getCustomers(@RequestParam("ids") Collection<Long> ids);
}
