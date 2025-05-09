package com.baedal.review.adapter.web.in;

import com.baedal.review.adapter.web.in.request.CreateReviewRequest;
import com.baedal.review.adapter.web.in.response.GetAverageScoreResponse;
import com.baedal.review.adapter.web.in.response.GetStoreTop10ReviewsResponse;
import com.baedal.review.adapter.web.mapper.WebReviewMapper;
import com.baedal.review.application.port.dto.CreateReviewCommand;
import com.baedal.review.application.port.dto.PagedResponse;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.dto.StoreReviewSummary;
import com.baedal.review.application.service.ReviewCommandService;
import com.baedal.review.application.service.ReviewQueryService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/review/v0")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewCommandService commandService;

  private final ReviewQueryService reviewQueryService;

  private final WebReviewMapper mapper;

  @PostMapping
  public ResponseEntity<Void> createReview(@RequestBody CreateReviewRequest request) {
    CreateReviewCommand.Request commandReq = mapper.toCommand(request);
    Long reviewId = commandService.create(commandReq);

    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{reviewId}")
        .buildAndExpand(reviewId)
        .toUri();

    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long reviewId) {
    commandService.delete(reviewId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{storeId}/summary")
  public ResponseEntity<GetStoreTop10ReviewsResponse> getStoreTop10Reviews(
      @PathVariable("storeId") Long storeId) {
    List<StoreReviewSummary> summaries = reviewQueryService.findTop10ReviewSummary(storeId);
    GetStoreTop10ReviewsResponse response = GetStoreTop10ReviewsResponse.builder()
        .data(summaries)
        .build();

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{storeId}/average-score")
  public ResponseEntity<GetAverageScoreResponse> getAverageScore(@PathVariable Long storeId) {
    Double average = reviewQueryService.findAverageScoreOfStore(storeId);
    GetAverageScoreResponse response = mapper.toResponse(average);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{storeId}")
  public ResponseEntity<PagedResponse<ReviewDetail>> getReviewDetails(
      @PathVariable Long storeId,
      @RequestParam(defaultValue = "0") Integer pageNumber,
      @RequestParam(defaultValue = "10") Integer size) {
    size = Integer.max(size, 10);

    PagedResponse<ReviewDetail> response = reviewQueryService
        .findReviewDetailsByStore(storeId, pageNumber, size);
    return ResponseEntity.ok(response);
  }
}
