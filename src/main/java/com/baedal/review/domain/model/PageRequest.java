package com.baedal.review.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageRequest {

  private final int pageNumber;

  private final int pageSize;
}
