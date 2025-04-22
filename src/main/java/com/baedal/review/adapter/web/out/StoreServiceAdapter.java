package com.baedal.review.adapter.web.out;

import com.baedal.review.adapter.web.out.client.StoreClient;
import com.baedal.review.adapter.web.out.mapper.StoreMapper;
import com.baedal.review.adapter.web.out.response.GetStoreResponse;
import com.baedal.review.application.port.out.StorePort;
import com.baedal.review.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreServiceAdapter implements StorePort {

  private final StoreMapper mapper;

  private final StoreClient client;

  public Store getStore(Long storeId) {
    GetStoreResponse response = client.getStore(storeId);
    return mapper.toStore(response);
  }
}
