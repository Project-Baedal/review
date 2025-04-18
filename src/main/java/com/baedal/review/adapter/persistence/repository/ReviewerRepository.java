package com.baedal.review.adapter.persistence.repository;

import com.baedal.review.adapter.persistence.entity.ReviewerAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerRepository extends JpaRepository<ReviewerAggregate, Long> {

}
