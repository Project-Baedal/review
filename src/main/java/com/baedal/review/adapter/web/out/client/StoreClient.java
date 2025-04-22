package com.baedal.review.adapter.web.out.client;

import com.baedal.review.adapter.web.out.response.GetStoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "store-service", url = "${services.store.url}")
public interface StoreClient {

  @GetMapping("/v0/reviewInfo/{storeId}")
  GetStoreResponse getStore(@PathVariable("storeId") Long storeId);
}
