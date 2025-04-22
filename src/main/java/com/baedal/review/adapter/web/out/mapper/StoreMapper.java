package com.baedal.review.adapter.web.out.mapper;

import com.baedal.review.adapter.web.out.response.GetStoreResponse;
import com.baedal.review.domain.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {

  @Mapping(source = "storeId", target = "id")
  Store toStore(GetStoreResponse response);
}
