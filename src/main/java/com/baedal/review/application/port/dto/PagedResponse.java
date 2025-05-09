package com.baedal.review.application.port.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PagedResponse<T> {

  private List<T> data;

  private boolean hasNext;

  private int pageNumber;

  private int size;
}
