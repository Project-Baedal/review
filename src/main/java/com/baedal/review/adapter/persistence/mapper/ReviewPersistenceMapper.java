package com.baedal.review.adapter.persistence.mapper;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewAttachmentEntity;
import com.baedal.review.domain.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewPersistenceMapper {

  Review toDomain(ReviewAggregate aggregate);

  Review.ReviewAttachment toDomain(ReviewAttachmentEntity entity);
}
