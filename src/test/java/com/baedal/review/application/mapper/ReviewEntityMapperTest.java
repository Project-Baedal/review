package com.baedal.review.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.baedal.review.domain.model.ReviewScore;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ReviewEntityMapperTest {

  private ReviewEntityMapper mapper = Mappers.getMapper(ReviewEntityMapper.class);

  @Test
  void enumToInt() {
    for (ReviewScore score : ReviewScore.values()) {
      Integer value = mapper.toScore(score);
      assertThat(value).isEqualTo(score.getValue());
    }
  }
}
