package com.baedal.review.adapter.web.in;

import com.baedal.review.adapter.web.in.request.CreateReviewRequest;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.in.ReviewCRUDUsecase;
import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v0")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewCRUDUsecase reviewCRUDUsecase;

  @GetMapping("/")
  public ResponseEntity<ReviewDetail> getReview(@PathVariable Long reviewId) {
    ReviewDetail reviewDetail = reviewCRUDUsecase.findReviewDetail(reviewId);
    return ResponseEntity.ok(reviewDetail);
  }

  @PostMapping("/")
  public ResponseEntity<Void> createReview(@RequestBody CreateReviewRequest request) {
    Long reviewId = reviewCRUDUsecase.createReview(
        request.getCustomerId(),
        request.getStoreId(),
        request.getOrderId(),
        request.getScore(),
        request.getContent(),
        request.getAttchments());

    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{reviewId}")
        .buildAndExpand(reviewId)
        .toUri();

    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping("/")
  public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
    reviewCRUDUsecase.deleteReview(reviewId);
    return ResponseEntity.noContent().build();
  }
}
