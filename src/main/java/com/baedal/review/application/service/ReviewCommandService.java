package com.baedal.review.application.service;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.application.mapper.ReviewEntityMapper;
import com.baedal.review.application.port.dto.CreateReviewCommand;
import com.baedal.review.application.port.out.ReviewCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

  private final ReviewCommandPort commandPort;

  private final ReviewEntityMapper mapper;

  @Transactional
  public Long create(CreateReviewCommand.Request request) {
    ReviewAggregate entity = mapper.toEntity(request);
    return commandPort.save(entity);
  }

  @Transactional
  public void delete(Long reviewId) {
    // TODO: 삭제 기능 권한 검증. customer 본인/ owner...
    ReviewAggregate entity = new ReviewAggregate(reviewId);
    commandPort.delete(entity);
  }
}
