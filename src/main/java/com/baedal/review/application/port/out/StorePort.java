package com.baedal.review.application.port.out;

import com.baedal.review.domain.model.Store;

public interface StorePort {

  Store getStore(Long storeId);
}
