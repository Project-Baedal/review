package com.baedal.review.application.service;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.adapter.persistence.entity.ReviewScore;
import com.baedal.review.adapter.persistence.repository.ReviewRepository;
import com.baedal.review.application.mapper.ReviewDetailMapper;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.application.port.out.ReviewQueryPort;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.Store;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewQueryServiceTest {

  @InjectMocks
  private ReviewQueryService reviewQueryService;

  @Mock
  private ReviewQueryPort queryPort;

  @Mock
  private CustomerPort customerPort;

  @Spy
  private ReviewDetailMapper reviewDetailMapper = Mappers.getMapper(ReviewDetailMapper.class);

  @Test
  void findReviewDetail_ShouldReturnMappedReviewDetail() {
    // given
    Long reviewId = 1L;
    ReviewAggregate review = ReviewAggregate
        .builder()
        .reviewerId(1L)
        .storeId(2L)
        .orderId(3L)
        .score(ReviewScore.FIVE)
        .content("text")
        .attachments(List.of())
        .build();

    ReflectionTestUtils.setField(review, "id", reviewId);
    ReflectionTestUtils.setField(review, "createdAt", LocalDateTime.now());

    Customer customer = new Customer(1L, "nick");
    Store store = new Store(2L, "mick");

    when(queryPort.findById(reviewId)).thenReturn(review);
    when(customerPort.getCustomer(review.getReviewerId())).thenReturn(customer);

    // when
    ReviewDetail result = reviewQueryService.findReviewDetail(reviewId);

    // then
    verify(queryPort).findById(reviewId);
    verify(customerPort).getCustomer(review.getReviewerId());
  }

}