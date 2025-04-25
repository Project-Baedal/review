package com.baedal.review.application.mapper;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewAttachment;
import com.baedal.review.application.port.dto.CreateReviewCommand;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewEntityMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "reviewerId", source = "customerId")
  @Mapping(target = "attachments", source = "r.attachments")
  ReviewAggregate toEntity(CreateReviewCommand.Request r);

  default List<ReviewAttachment> toEntity(List<String> urls) {
    return urls.stream()
        .map(str -> ReviewAttachment.builder()
            .url(str)
            .build())
        .toList();
  }
}
