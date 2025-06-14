package xyz.catequest.spring.domain.question.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.question.entity.Question;

@Getter
@RequiredArgsConstructor
public class QuestionResponse {
  private final Long id;
  private final String question;
  private final String category;
  private final Long categoryInId;

  public static QuestionResponse from(Question question) {
    return new QuestionResponse(question.getId(), question.getQuestion(), question.getCategory(), question.getCategoryInId());
  }
}
