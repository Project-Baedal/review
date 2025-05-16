package com.baedal.review.adapter.persistence.out;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.mapper.ReviewPersistenceMapper;
import com.baedal.review.adapter.persistence.projection.ReviewSummaryProjection;
import com.baedal.review.adapter.persistence.repository.ReviewRepository;
import com.baedal.review.application.port.out.ReviewQueryPort;
import com.baedal.review.domain.model.Review;
import com.baedal.review.domain.model.ReviewSummary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewQueryAdapter implements ReviewQueryPort {

  private final ReviewRepository repository;

  private final ReviewPersistenceMapper mapper;

  public Review findById(Long reviewId) {
    ReviewAggregate aggregate = repository.findById(reviewId)
        .orElseThrow(() -> new RuntimeException("해당 Id로 리뷰를 찾을 수 없음"));
    return mapper.toDomain(aggregate);
  }

  public List<ReviewSummary> findTop10ReviewOfStore(Long storeId) {
    // TODO: 어떤 기준으로 10개 뽑을지?
    return repository.findTop10ByStoreIdOrderByIdDesc(storeId).stream()
        .map(mapper::toDomain)
        .toList();
  }

  public Double calculateAverageScore(Long storeId) {
    return repository.averageScoreOfStore(storeId);
  }

  public Slice<Review> findByStoreId(Long storeId, Integer number, Integer size) {
    Slice<ReviewAggregate> slice = repository.findByStoreId(storeId, PageRequest.of(number, size));
    return slice.map(mapper::toDomain);
  }
}
