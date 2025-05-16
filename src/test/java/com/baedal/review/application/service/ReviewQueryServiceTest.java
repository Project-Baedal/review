package com.baedal.review.application.service;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import com.baedal.review.application.mapper.ReviewDetailMapper;
import com.baedal.review.application.port.dto.PagedResponse;
import com.baedal.review.application.port.dto.ReviewDetail;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.application.port.out.ReviewQueryPort;
import com.baedal.review.domain.model.Customer;
import com.baedal.review.domain.model.Review;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewQueryServiceTest {
  @InjectMocks
  private ReviewQueryService service;

  @Mock
  private ReviewQueryPort reviewQueryPort;

  @Mock
  private CustomerPort customerPort;

  @Mock
  private ReviewDetailMapper reviewDetailMapper;

  @Test
  void findReviewDetailsByStore_shouldReturnPagedResponse() {
    // given
    Long storeId = 1L;
    int page = 0;
    int size = 2;

    // Mock review aggregates
    Review review1 = mock(Review.class);
    Review review2 = mock(Review.class);

    when(review1.getReviewerId()).thenReturn(101L);
    when(review2.getReviewerId()).thenReturn(102L);

    List<Review> reviewList = List.of(review1, review2);
    Slice<Review> slice = new SliceImpl<>(reviewList, PageRequest.of(page, size), true);

    when(reviewQueryPort.findByStoreId(storeId, page, size)).thenReturn(slice);

    // Mock customers
    Customer customer1 = Customer.builder().id(101L).name("Alice").build();
    Customer customer2 = Customer.builder().id(102L).name("Bob").build();

    when(customerPort.getCustomersByIds(List.of(101L, 102L)))
        .thenReturn(List.of(customer1, customer2));

    // Mock review details
    ReviewDetail detail1 = mock(ReviewDetail.class);
    ReviewDetail detail2 = mock(ReviewDetail.class);

    when(reviewDetailMapper.toReviewDetail(review1, customer1)).thenReturn(detail1);
    when(reviewDetailMapper.toReviewDetail(review2, customer2)).thenReturn(detail2);

    List<ReviewDetail> mappedList = List.of(detail1, detail2);
    PagedResponse<ReviewDetail> expected = new PagedResponse<>(mappedList, true, page, size);

    when(reviewDetailMapper.toPagedResponse(eq(mappedList), eq(true), eq(page), eq(size)))
        .thenReturn(expected);

    // when
    PagedResponse<ReviewDetail> actual = service.findReviewDetailsByStore(storeId, page, size);

    // then
    assertNotNull(actual);
    assertEquals(2, actual.getData().size());
    assertEquals(expected, actual);
    assertTrue(actual.isHasNext());

    // verify
    verify(reviewQueryPort).findByStoreId(storeId, page, size);
    verify(customerPort).getCustomersByIds(List.of(101L, 102L));
    verify(reviewDetailMapper).toReviewDetail(review1, customer1);
    verify(reviewDetailMapper).toReviewDetail(review2, customer2);
    verify(reviewDetailMapper).toPagedResponse(mappedList, true, page, size);
  }
}
