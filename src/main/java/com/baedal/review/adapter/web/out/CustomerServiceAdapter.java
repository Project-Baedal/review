package com.baedal.review.adapter.web.out;

import com.baedal.review.adapter.web.out.client.CustomerClient;
import com.baedal.review.adapter.web.out.mapper.CustomerMapper;
import com.baedal.review.adapter.web.out.response.GetCustomerResponse;
import com.baedal.review.adapter.web.out.response.GetCustomersResponse;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.domain.model.Customer;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerServiceAdapter implements CustomerPort {

  private final CustomerMapper mapper;

  private final CustomerClient client;

  public Customer getCustomer(Long customerId) {
    GetCustomerResponse response = client.getCustomer(customerId);
    return mapper.toReviewer(response);
  }

  @Override
  public Collection<Customer> getCustomersByIds(Collection<Long> ids) {
    GetCustomersResponse response = client.getCustomers(ids);
    return mapper.toReviewers(response);
  }
}
