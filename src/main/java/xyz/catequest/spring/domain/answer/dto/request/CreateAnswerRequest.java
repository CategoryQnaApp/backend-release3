package xyz.catequest.spring.domain.answer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAnswerRequest {

  @NotBlank
  @Size(min = 1)
  private final String content;

  @NotBlank private final Long questionId;

  @NotBlank private final String envelope;

  @Positive private final Long usedItemCount;
}
