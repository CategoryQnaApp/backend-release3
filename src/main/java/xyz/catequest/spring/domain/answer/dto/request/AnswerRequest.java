package xyz.catequest.spring.domain.answer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnswerRequest {

  @NotBlank
  @Size(min = 1)
  private final String content;

  @NotBlank private final String envelope;

  @PositiveOrZero private final Long usedItemCount;
}
