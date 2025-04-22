package com.baedal.review.adapter.web.out.response;

import lombok.Builder;

@Builder
public record GetStoreResponse(Long storeId, String name) {

}
