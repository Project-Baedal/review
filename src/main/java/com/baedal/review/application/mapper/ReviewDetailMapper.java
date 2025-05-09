package com.baedal.review.application.mapper;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewAttachment;
import com.baedal.review.application.port.dto.PagedResponse;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.domain.model.Customer;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewDetailMapper {

  default ReviewDetail toReviewDetail(ReviewAggregate review, Customer customer) {
    return ReviewDetail.builder()
        .reviewId(review.getId())
        .content(review.getContent())
        .reviewScore(review.getScore())
        .createdAt(review.getCreatedAt())
        .reviewer(toReviewerInfo(customer))
        .attachments(toAttachmentInfoList(review.getAttachments()))
        .build();
  }

  @Mapping(source = "id", target = "customerId")
  ReviewDetail.ReviewerInfo toReviewerInfo(Customer customer);

  @Mapping(target = "attachmentId", source = "id")
  ReviewDetail.AttachmentInfo toAttachmentInfo(ReviewAttachment attachment);

  @Mapping(target = "attachmentId", source = "list.id")
  List<ReviewDetail.AttachmentInfo> toAttachmentInfoList(List<ReviewAttachment> list);

  @Mapping(target = "content", source = "data")
  PagedResponse<ReviewDetail> toPagedResponse(
      List<ReviewDetail> data,
      Boolean hasNext,
      Integer pageNumber,
      Integer size);
}
