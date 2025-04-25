package com.baedal.review.adapter.persistence.repository;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.projection.ReviewSummaryProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewAggregate, Long> {

  List<ReviewSummaryProjection> findTop10ByStoreIdOrderByIdDesc(Long storeId);
}
