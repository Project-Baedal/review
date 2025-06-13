package com.baedal.review.application.mapper;

import com.baedal.review.application.port.dto.PagedResponse;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.Review;
import java.util.Collection;
import java.util.HashMap;
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
      Collection<Customer> customers) {
    Map<Long, Customer> customerMap = makeCustomerMap(customers);
    return reviews.stream()
        .map(review -> toReviewDetail(review, customerMap.get(review.getReviewerId())))
        .toList();
  }

  private Map<Long, Customer> makeCustomerMap(Collection<Customer> customers) {
    HashMap<Long, Customer> customerMap = new HashMap<>();
    for (Customer customer : customers) {
      customerMap.put(customer.getId(), customer);
    }
    return customerMap;
  }

  PagedResponse<ReviewDetail> toPagedResponse(
      List<ReviewDetail> data,
      Boolean hasNext,
      Integer pageNumber,
      Integer size);
}
