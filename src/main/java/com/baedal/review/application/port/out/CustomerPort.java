package com.baedal.review.application.port.out;

import com.baedal.review.domain.model.Customer;
import java.util.Collection;

public interface CustomerPort {

  Customer getCustomer(Long customerId);

  Collection<Customer> getCustomersByIds(Collection<Long> ids);
}
