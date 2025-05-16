package com.baedal.review.domain.model;

import java.util.Iterator;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.util.Streamable;

@Getter
@Builder
public class DomainSlice<T> implements Streamable<T> {

  private List<T> content;

  private Boolean hasNext;

  private Integer number;

  private Integer size;

  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public Iterator<T> iterator() {
    return content.iterator();
  }
}
