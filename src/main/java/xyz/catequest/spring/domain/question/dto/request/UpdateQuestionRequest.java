package xyz.catequest.spring.domain.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.catequest.spring.domain.question.enums.Category;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.InvalidRequestException;

@Getter
@AllArgsConstructor
public class UpdateQuestionRequest {
  private String question;
  private Category category;

  public UpdateQuestionRequest(String question) {
    if (question == null || question.isBlank()) {
      throw new InvalidRequestException(ErrorMessage.TYPE_MISMATCH);
    }
    this.question = question;
  }

  public UpdateQuestionRequest(Category category) {
    if (category == null) {
      throw new InvalidRequestException(ErrorMessage.TYPE_MISMATCH);
    }
    this.category = category;
  }

  public boolean questionIsNull() {
    return question == null || question.isBlank();
  }

  public boolean categoryIsNull() {
    return category == null;
  }
}
