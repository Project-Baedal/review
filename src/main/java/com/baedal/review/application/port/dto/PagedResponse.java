package com.baedal.review.application.port.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PagedResponse<T> {

  private List<T> content;

  private boolean hasNext;

  private int pageNumber;

  private int size;
}
