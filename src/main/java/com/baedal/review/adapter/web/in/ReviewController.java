package com.baedal.review.adapter.web.in;

import com.baedal.review.adapter.web.in.request.CreateReviewRequest;
import com.baedal.review.adapter.web.in.response.GetStoreTop10ReviewsResponse;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.dto.StoreReviewSummary;
import com.baedal.review.application.port.in.ReviewCRUDUsecase;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v0")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewCRUDUsecase reviewCRUDUsecase;

  @GetMapping("/{reviewId}")
  public ResponseEntity<ReviewDetail> getReview(@PathVariable("reviewId") Long id) {
    ReviewDetail reviewDetail = reviewCRUDUsecase.findReviewDetail(id);
    return ResponseEntity.ok(reviewDetail);
  }

  @PostMapping
  public ResponseEntity<Void> createReview(@RequestBody CreateReviewRequest request) {
    Long reviewId = reviewCRUDUsecase.createReview(
        request.customerId(),
        request.storeId(),
        request.orderId(),
        request.score(),
        request.content(),
        request.attachments());

    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{reviewId}")
        .buildAndExpand(reviewId)
        .toUri();

    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long reviewId) {
    reviewCRUDUsecase.deleteReview(reviewId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{storeId}/summary")
  public ResponseEntity<GetStoreTop10ReviewsResponse> getStoreTop10Reviews(
      @PathVariable("storeId") Long storeId) {
    List<StoreReviewSummary> summaries = reviewCRUDUsecase.findTop10ReviewSummary(storeId);
    GetStoreTop10ReviewsResponse response = GetStoreTop10ReviewsResponse.builder()
        .data(summaries)
        .build();

    return ResponseEntity.ok(response);
  }
}
