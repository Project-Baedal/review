package com.baedal.review.adapter.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewAggregate {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long reviewerId;

  @Column(nullable = false)
  private Long storeId;

  @Column(nullable = false)
  private Long orderId;

  @Column(nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private ReviewScore score;

  @CreationTimestamp
  private LocalDateTime createdAt;

  private String content;

  @OneToMany(
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @JoinColumn(name = "review_id")
  private List<ReviewAttachment> attachments = new ArrayList<>();

  public ReviewAggregate(Long id) {
    this.id = id;
  }
}
