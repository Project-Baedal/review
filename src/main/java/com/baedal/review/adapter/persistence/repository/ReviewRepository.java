package com.baedal.review.adapter.persistence.repository;

import com.baedal.review.adapter.persistence.entity.ReviewAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewAggregate, Long> {

}
