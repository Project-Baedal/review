package com.baedal.review.adapter.persistence.repository;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.projection.ReviewSummaryProjection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<ReviewAggregate, Long> {

  List<ReviewSummaryProjection> findTop10ByStoreIdOrderByIdDesc(Long storeId);

  @Query("SELECT AVG(r.score) FROM ReviewAggregate r WHERE r.storeId = :storeId")
  Double averageScoreOfStore(@Param("storeId") Long storeId);

  Slice<ReviewAggregate> findByStoreId(Long storeId, Pageable pageable);
}
