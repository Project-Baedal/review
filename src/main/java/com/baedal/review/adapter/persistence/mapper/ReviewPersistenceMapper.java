package com.baedal.review.adapter.persistence.mapper;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewAttachmentEntity;
import com.baedal.review.adapter.persistence.projection.ReviewSummaryProjection;
import com.baedal.review.domain.model.DomainSlice;
import com.baedal.review.domain.model.Review;
import com.baedal.review.domain.model.ReviewSummary;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Slice;

@Mapper(componentModel = "spring")
public interface ReviewPersistenceMapper {

  Review toDomain(ReviewAggregate aggregate);

  List<Review> toDomainList(List<ReviewAggregate> list);

  Review.ReviewAttachment toDomain(ReviewAttachmentEntity entity);

  ReviewSummary toDomain(ReviewSummaryProjection projection);

  default DomainSlice<Review> toDomain(Slice<ReviewAggregate> slice){
    return DomainSlice.<Review>builder()
        .content(toDomainList(slice.getContent()))
        .hasNext(slice.hasNext())
        .number(slice.getNumber())
        .size(slice.getSize())
        .build();
  }
}
