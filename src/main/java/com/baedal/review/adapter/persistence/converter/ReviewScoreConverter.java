package com.baedal.review.adapter.persistence.converter;

import com.baedal.review.adapter.persistence.entity.ReviewScore;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReviewScoreConverter implements AttributeConverter<ReviewScore, Integer> {

  @Override
  public Integer convertToDatabaseColumn(ReviewScore attribute) {
    if(attribute == null){
      return null;
    }
    return attribute.getValue();
  }

  @Override
  public ReviewScore convertToEntityAttribute(Integer dbData) {
    if(dbData == null){
      return null;
    }
    return ReviewScore.fromValue(dbData);
  }
}
