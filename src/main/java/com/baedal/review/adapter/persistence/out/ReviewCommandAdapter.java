package com.baedal.review.adapter.persistence.out;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.repository.ReviewRepository;
import com.baedal.review.application.port.out.ReviewCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewCommandAdapter implements ReviewCommandPort {

  private final ReviewRepository reviewRepository;

  @Override
  public Long save(ReviewAggregate entity) {
    ReviewAggregate save = reviewRepository.save(entity);
    return save.getId();
  }

  @Override
  public void delete(ReviewAggregate entity) {
    reviewRepository.delete(entity);
  }
}
