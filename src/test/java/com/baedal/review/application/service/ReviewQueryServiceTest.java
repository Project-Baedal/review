package com.baedal.review.application.service;

import com.baedal.review.application.mapper.ReviewDetailMapper;
import com.baedal.review.application.port.out.CustomerPort;
import com.baedal.review.application.port.out.ReviewQueryPort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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

}