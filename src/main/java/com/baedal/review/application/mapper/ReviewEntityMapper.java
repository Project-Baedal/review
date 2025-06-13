package com.baedal.review.application.mapper;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewAttachmentEntity;
import com.baedal.review.application.port.dto.CreateReviewCommand;
import com.baedal.review.domain.model.ReviewScore;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewEntityMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "reviewerId", source = "customerId")
  @Mapping(target = "attachments", source = "r.attachments")
  @Mapping(target = "score", source = "r.score")
  ReviewAggregate toEntity(CreateReviewCommand.Request r);

  default List<ReviewAttachmentEntity> toEntity(List<String> urls) {
    return urls.stream()
        .map(str -> ReviewAttachmentEntity.builder()
            .url(str)
            .build())
        .toList();
  }

  default Integer toScore(ReviewScore score) {
    return score.getValue();
  }
}
