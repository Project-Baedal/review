package com.baedal.review.adapter.persistence.entity;

import com.baedal.review.adapter.persistence.converter.ReviewScoreConverter;
import com.baedal.review.domain.model.ReviewScore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewAggregate {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Long reviewerId;

  @Column(nullable = false)
  private Long storeId;

  @Column(nullable = false)
  private Long orderId;

  @Column(nullable = false)
  @Convert(converter = ReviewScoreConverter.class)
  private ReviewScore score;

  @CreationTimestamp
  private LocalDateTime createdAt;

  private String content;

  @OneToMany(
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @JoinColumn(name = "review_id")
  private List<ReviewAttachmentEntity> attachments = new ArrayList<>();

  public ReviewAggregate(Long id) {
    this.id = id;
  }

  @Builder
  public ReviewAggregate(Long id, Long reviewerId, Long storeId, Long orderId, ReviewScore score,
      LocalDateTime createdAt, String content, List<ReviewAttachmentEntity> attachments) {
    this.id = id;
    this.reviewerId = reviewerId;
    this.storeId = storeId;
    this.orderId = orderId;
    this.score = score;
    this.createdAt = createdAt;
    this.content = content;
    this.attachments = attachments;
  }
}
