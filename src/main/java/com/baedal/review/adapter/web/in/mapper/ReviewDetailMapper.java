package com.baedal.review.adapter.web.in.mapper;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewAttachment;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.Store;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewDetailMapper {

  default ReviewDetail toReviewDetail(ReviewAggregate review, Customer customer, Store store) {
    return ReviewDetail.builder()
        .reviewId(review.getId())
        .content(review.getContent())
        .reviewScore(review.getScore())
        .createdAt(review.getCreatedAt())
        .reviewer(toReviewerInfo(customer))
        .store(toStoreInfo(store))
        .attachments(toAttachmentInfoList(review.getAttachments()))
        .build();
  }

  @Mapping(source = "id", target = "reviewerId")
  ReviewDetail.ReviewerInfo toReviewerInfo(Customer customer);

  @Mapping(source = "id", target = "storeId")
  ReviewDetail.StoreInfo toStoreInfo(Store store);

  @Mapping(source = "id", target = "attachmentId")
  ReviewDetail.AttachmentInfo toAttachmentInfo(ReviewAttachment attachment);

  List<ReviewDetail.AttachmentInfo> toAttachmentInfoList(List<ReviewAttachment> list);
}
