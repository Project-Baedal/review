package com.baedal.review.application.service;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewScore;
import com.baedal.review.application.mapper.ReviewEntityMapper;
import com.baedal.review.application.port.dto.CreateReviewCommand;
import com.baedal.review.application.port.dto.CreateReviewCommand.Request;
import com.baedal.review.application.port.out.ReviewCommandPort;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewCommandServiceTest {

  @InjectMocks
  private ReviewCommandService service;

  @Mock
  private ReviewCommandPort commandPort;

  @Spy
  private ReviewEntityMapper mapper = Mappers.getMapper(ReviewEntityMapper.class);

  @Test
  void createReview_ShouldReturnSavedReviewId() {
    // given
    Long customerId = 1L;
    Long storeId = 2L;
    Long orderId = 3L;
    ReviewScore score = ReviewScore.FIVE;
    String content = "Great!";
    List<String> attachments = List.of("url1", "url2");

    CreateReviewCommand.Request request = makeCommandRequest(
        customerId,
        storeId,
        orderId,
        score,
        content,
        attachments);

    long mockReviewId = 10L;
    ReviewAggregate review = createMock(request, mockReviewId);

    when(commandPort.save(any(ReviewAggregate.class)))
        .thenReturn(mockReviewId);

    // when
    Long result = service.create(request);

    // then
    assertEquals(mockReviewId, result);
    verify(commandPort).save(any(ReviewAggregate.class));
  }

  private ReviewAggregate createMock(
      CreateReviewCommand.Request r,
      long mockReviewId) {
    ReviewAggregate mockReview = ReviewAggregate.builder()
        .reviewerId(r.getCustomerId())
        .storeId(r.getStoreId())
        .orderId(r.getOrderId())
        .score(r.getScore())
        .content(r.getContent())
        .attachments(List.of())
        .build();
    ReflectionTestUtils.setField(mockReview, "id", mockReviewId);

    return mockReview;
  }

  private Request makeCommandRequest(
      Long customerId,
      Long storeId,
      Long orderId,
      ReviewScore score,
      String content,
      List<String> attachments) {
    return Request.builder()
        .customerId(customerId)
        .storeId(storeId)
        .orderId(orderId)
        .score(score)
        .content(content)
        .attachments(attachments)
        .build();
  }

  @Test
  void deleteReview_ShouldCallDeleteWithCorrectId() {
    // given
    Long reviewId = 42L;

    // when
    service.delete(reviewId);

    // then
    ArgumentCaptor<ReviewAggregate> captor = ArgumentCaptor.forClass(ReviewAggregate.class);
    verify(commandPort).delete(captor.capture());

    assertEquals(reviewId, captor.getValue().getId());
  }
}