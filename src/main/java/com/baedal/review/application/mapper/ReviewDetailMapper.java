package com.baedal.review.application.mapper;

import com.baedal.review.application.port.dto.PagedResponse;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.Review;
import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewDetailMapper {

  default ReviewDetail toReviewDetail(Review review, Customer customer) {
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
  ReviewDetail.AttachmentInfo toAttachmentInfo(Review.ReviewAttachment attachment);

  @Mapping(target = "attachmentId", source = "list.id")
  List<ReviewDetail.AttachmentInfo> toAttachmentInfoList(List<Review.ReviewAttachment> list);

  default List<ReviewDetail> toReviewDetailList(
      List<Review> reviews,
      Map<Long, Customer> customers) {
    return reviews.stream()
        .map(review -> toReviewDetail(review, customers.get(review.getReviewerId())))
        .toList();
  }

  PagedResponse<ReviewDetail> toPagedResponse(
      List<ReviewDetail> data,
      Boolean hasNext,
      Integer pageNumber,
      Integer size);
}
