package com.baedal.review.application.service;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewScore;
import com.baedal.review.adapter.persistence.repository.ReviewRepository;
import com.baedal.review.adapter.web.in.mapper.ReviewDetailMapper;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.application.port.out.StorePort;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.Store;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
class ReviewCRUDServiceTest {

  @InjectMocks
  private ReviewCRUDService reviewCRUDService;

  @Mock
  private ReviewRepository reviewRepository;

  @Mock
  private CustomerPort customerPort;

  @Mock
  private StorePort storePort;

  @Spy
  private ReviewDetailMapper reviewDetailMapper = Mappers.getMapper(ReviewDetailMapper.class);

  @Test
  void createReview_ShouldReturnSavedReviewId() {
    // given
    Long customerId = 1L;
    Long storeId = 2L;
    Long orderId = 3L;
    ReviewScore score = ReviewScore.FIVE;
    String content = "Great!";
    List<String> attachments = List.of("url1", "url2");

    ReviewAggregate mockReview = ReviewAggregate.create(
        customerId,
        storeId,
        orderId,
        score,
        content,
        attachments);
    ReflectionTestUtils.setField(mockReview, "id", 10L);

    when(reviewRepository.save(any(ReviewAggregate.class)))
        .thenReturn(mockReview);

    // when
    Long result = reviewCRUDService.createReview(customerId, storeId, orderId, score, content,
        attachments);

    // then
    assertEquals(10L, result);
    verify(reviewRepository).save(any(ReviewAggregate.class));
  }

  @Test
  void deleteReview_ShouldCallDeleteWithCorrectId() {
    // given
    Long reviewId = 42L;

    // when
    reviewCRUDService.deleteReview(reviewId);

    // then
    ArgumentCaptor<ReviewAggregate> captor = ArgumentCaptor.forClass(ReviewAggregate.class);
    verify(reviewRepository).delete(captor.capture());

    assertEquals(reviewId, captor.getValue().getId());
  }

  @Test
  void findReviewDetail_ShouldReturnMappedReviewDetail() {
    // given
    Long reviewId = 1L;
    ReviewAggregate review = ReviewAggregate.create(1L, 2L, 3L, ReviewScore.FIVE, "text",
        List.of());
    ReflectionTestUtils.setField(review, "id", reviewId);
    ReflectionTestUtils.setField(review, "createdAt", LocalDateTime.now());

    Customer customer = new Customer(1L, "nick"); // mock or stub your own
    Store store = new Store(2L, "mick");         // mock or stub your own

    when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
    when(customerPort.getCustomer(review.getReviewerId())).thenReturn(customer);
    when(storePort.getStore(review.getStoreId())).thenReturn(store);

    // when
    ReviewDetail result = reviewCRUDService.findReviewDetail(reviewId);

    // then
    verify(reviewRepository).findById(reviewId);
    verify(customerPort).getCustomer(review.getReviewerId());
    verify(storePort).getStore(review.getStoreId());
  }

}