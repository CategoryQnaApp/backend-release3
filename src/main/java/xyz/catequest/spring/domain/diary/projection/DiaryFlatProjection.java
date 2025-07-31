package xyz.catequest.spring.domain.diary.projection;

import java.time.LocalDateTime;

public interface DiaryFlatProjection {
  Long getDiaryId();

  String getContent();

  String getImageUrl();

  String getEmoticons();

  String getTagName();

  LocalDateTime getSavedAt();
}
