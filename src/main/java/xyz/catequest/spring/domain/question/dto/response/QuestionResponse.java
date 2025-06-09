package xyz.catequest.spring.domain.question.dto.response;


import lombok.Getter;
import xyz.catequest.spring.domain.question.entity.Question;

@Getter
public class QuestionResponse {
  private Long id;
  private String question;
  private String category;
  private Long categoryInid;

  public QuestionResponse(Long id, String question, String category, Long categoryInId) {
    this.id = id;
    this.question = question;
    this.category = category;
    this.categoryInid = categoryInId;
  }

  public static QuestionResponse from(Question question) {
    return new QuestionResponse(question.getId(), question.getQuestion(), question.getCategory(), question.getCategoryInId());
  }
}
