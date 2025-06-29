package xyz.catequest.spring.domain.answer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAnswerRequest {

  @NotBlank
  @Size(min = 1)
  private final String answer;

//  private final String envlope;
//
//  private final Long characterLimitItemCount;
}
