package com.baedal.review.adapter.web.out.mapper;

import com.baedal.review.adapter.web.out.response.GetStoreResponse;
import com.baedal.review.domain.model.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {

  Store toStore(GetStoreResponse response);
}
