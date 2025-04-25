package com.baedal.review.application.port.out;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.projection.ReviewSummaryProjection;
import java.util.List;
import org.springframework.data.domain.Slice;

// TODO: QueryDSL 같은 조회 최적화 리포지터리.
//  - 연관관계 조회 전략 적용하기.(근데 MSA임)
public interface ReviewQueryPort {

  ReviewAggregate findById(Long reviewId);

  List<ReviewSummaryProjection> findTop10ReviewOfStore(Long storeId);

  Double calculateAverageScore(Long storeId);

  Slice<ReviewAggregate> findByStoreId(Long storeId, Integer number, Integer size);
}
