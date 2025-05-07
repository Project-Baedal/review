package com.baedal.review.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer {

  private Long id;
  private String name;
}
