package com.baedal.review.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO: Reviewer 생성 시점. 가입 or 리뷰 생성(리뷰 처음)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewerAggregate {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private Long customerId;

  @OneToMany(mappedBy = "reviewer")
  private List<ReviewAggregate> reviewAggregates = new ArrayList<>();

  private Boolean deleted = false;

  public ReviewerAggregate(Long customerId) {
    this.customerId = customerId;
  }
}
