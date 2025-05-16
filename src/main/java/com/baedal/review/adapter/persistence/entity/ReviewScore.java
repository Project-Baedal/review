package com.baedal.review.adapter.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReviewScore {

  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5);

  private final Integer value;

  public static ReviewScore fromValue(Integer value) {
    for (ReviewScore score : values()) {
      if (score.getValue().equals(value)) {
        return score;
      }
    }
    throw new IllegalArgumentException("Invalid score value: " + value);
  }
}
