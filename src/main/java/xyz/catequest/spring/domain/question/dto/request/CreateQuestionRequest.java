package xyz.catequest.spring.domain.question.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.question.enums.Category;

@Getter
@RequiredArgsConstructor // 생성자 와 정적 스태틱 메소드
public class CreateQuestionRequest {

  @NotBlank
  @Size(min = 4, max = 255)
  private final String question;

  @NotNull private final Category category;
}
