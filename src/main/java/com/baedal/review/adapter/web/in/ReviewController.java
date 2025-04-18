package com.baedal.review.adapter.web.in;

import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.in.ReviewCRUDUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<?> createReview() {
    throw new RuntimeException("Not Impled");
  }

  @DeleteMapping("/")
  public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
    reviewCRUDUsecase.deleteReview(reviewId);
    return ResponseEntity.noContent().build();
  }
}
