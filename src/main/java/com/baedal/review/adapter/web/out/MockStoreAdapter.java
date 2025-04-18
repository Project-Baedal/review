package com.baedal.review.adapter.web.out;

import com.baedal.review.adapter.web.out.mapper.StoreMapper;
import com.baedal.review.adapter.web.out.response.GetStoreResponse;
import com.baedal.review.application.port.out.StorePort;
import com.baedal.review.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MockStoreAdapter implements StorePort {

  private final StoreMapper mapper;

  public Store getStore(Long storeId) {
    GetStoreResponse response = new GetStoreResponse(2L, "storeName");
    return mapper.toStore(response);
  }
}
