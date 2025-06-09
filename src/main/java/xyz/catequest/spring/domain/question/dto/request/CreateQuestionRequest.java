package xyz.catequest.spring.domain.question.dto.request;

import lombok.Getter;

@Getter
public class CreateQuestionRequest {
  private final String question;
  private final String category;

  public CreateQuestionRequest(String question, String category) {
    this.question = question;
    this.category = category;
  }
}
