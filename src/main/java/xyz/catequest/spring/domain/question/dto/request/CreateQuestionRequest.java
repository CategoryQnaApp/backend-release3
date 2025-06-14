package xyz.catequest.spring.domain.question.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // 생성자 와 정적 스태틱 메소드
public class CreateQuestionRequest {

  @NotBlank
  @Size(min = 4, max = 255)
  private final String question;

  @NotBlank
  @Size(min = 1, max = 10)
  private final String category;
}
