package xyz.catequest.spring.domain.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.catequest.spring.domain.question.enums.Category;

@Getter
@AllArgsConstructor
public class UpdateQuestionRequest {
  private String question;
  private Category category;

  public boolean questionIsNull() {
    return question == null || question.isBlank();
  }

  public boolean categoryIsNull() {
    return category == null;
  }
}
