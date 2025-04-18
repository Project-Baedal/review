package com.baedal.review.adapter.web.out;

import com.baedal.review.adapter.web.out.mapper.CustomerMapper;
import com.baedal.review.adapter.web.out.response.GetCustomerResponse;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MockCustomerAdapter implements CustomerPort {

  private final CustomerMapper mapper;

  public Customer getCustomer(Long customerId) {
    GetCustomerResponse response = new GetCustomerResponse(1L, "mockName");
    return mapper.toReviewer(response);
  }
}
