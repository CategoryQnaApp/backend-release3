package xyz.catequest.spring.domain.question.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateQuestionRequest {
  private final String question;

  private final String category;
}
