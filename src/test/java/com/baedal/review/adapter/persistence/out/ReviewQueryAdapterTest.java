package com.baedal.review.adapter.persistence.out;

import static org.assertj.core.api.Assertions.assertThat;

import com.baedal.review.adapter.persistence.mapper.ReviewPersistenceMapper;
import com.baedal.review.adapter.persistence.out.ReviewQueryAdapterTest.TestConfig;
import com.baedal.review.adapter.persistence.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("local")
@Import(TestConfig.class)
class ReviewQueryAdapterTest {

  @Autowired
  ReviewQueryAdapter reviewQueryAdapter;

  @Autowired
  ReviewRepository repository;

  @Test
  void contextLoads() {
    assertThat(reviewQueryAdapter).isNotNull();
  }

  @TestConfiguration
  @ComponentScan(basePackageClasses = ReviewPersistenceMapper.class)
  static class TestConfig {

    @Bean
    public ReviewQueryAdapter reviewQueryAdapter(
        ReviewRepository repository,
        ReviewPersistenceMapper mapper) {
      return new ReviewQueryAdapter(repository, mapper);
    }
  }
}
