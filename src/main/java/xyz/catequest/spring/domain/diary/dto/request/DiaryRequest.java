package xyz.catequest.spring.domain.diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class DiaryRequest {
  @NotBlank private final String content;
  private final List<String> emoticons;
  private final List<String> hashTagList;
  private final LocalDateTime savedTime;
}
