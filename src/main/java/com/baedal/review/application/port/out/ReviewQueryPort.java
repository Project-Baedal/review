package com.baedal.review.application.port.out;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.projection.ReviewSummaryProjection;
import com.baedal.review.domain.model.DomainSlice;
import com.baedal.review.domain.model.Review;
import com.baedal.review.domain.model.ReviewSummary;
import java.util.List;
import org.springframework.data.domain.Slice;

// TODO: QueryDSL 같은 조회 최적화 리포지터리.
//  - 연관관계 조회 전략 적용하기.(근데 MSA임)
public interface ReviewQueryPort {

  Review findById(Long reviewId);

  List<ReviewSummary> findTop10ReviewOfStore(Long storeId);

  Double calculateAverageScore(Long storeId);

  DomainSlice<Review> findByStoreId(Long storeId, Integer number, Integer size);
}
