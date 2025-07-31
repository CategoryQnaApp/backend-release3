package xyz.catequest.spring.domain.diary.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class DiaryRequest {
  @NotBlank private final String content;

  @Size(max = 5)
  @Valid
  @NotEmpty
  private final List<@NotBlank String> emoticons;

  @Size(max = 5)
  @Valid
  @NotEmpty
  private final List<@NotBlank String> tagList;

  // todo : 시간 format 정하기 ex) 2024-10-03 10:50
  private final LocalDateTime savedTime;
}
