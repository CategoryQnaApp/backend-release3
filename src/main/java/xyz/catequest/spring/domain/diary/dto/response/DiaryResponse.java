package xyz.catequest.spring.domain.diary.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.diary.entity.Diary;
import xyz.catequest.spring.global.utils.EmoticonUtils;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class DiaryResponse {
  private final Long id;
  private final String content;
  private final String imageURl;
  private final List<String> emoticons;
  private final List<String> tags;
  private final LocalDateTime savedAt;

  public static DiaryResponse from(Diary diary) {
    return new DiaryResponse(
        diary.getId(),
        diary.getContent(),
        diary.getImageUrl(),
        EmoticonUtils.fromString(diary.getEmoticons()),
        diary.getDiaryTags().stream().map(d -> d.getTag().getName()).toList(),
        diary.getSavedAt());
  }
}
