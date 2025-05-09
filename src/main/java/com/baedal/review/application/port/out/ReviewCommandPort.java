package com.baedal.review.application.port.out;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;

public interface ReviewCommandPort {

  Long save(ReviewAggregate entity);

  void delete(ReviewAggregate entity);
}
