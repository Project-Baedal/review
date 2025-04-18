package com.baedal.review.application.port.out;

import com.baedal.review.domain.model.Customer;

public interface CustomerPort {

  Customer getCustomer(Long customerId);
}
