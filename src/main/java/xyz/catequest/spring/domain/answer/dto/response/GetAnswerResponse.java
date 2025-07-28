package xyz.catequest.spring.domain.answer.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.answer.entity.Answer;

@Getter
@NoArgsConstructor
public class GetAnswerResponse {

  private String answer;

  public GetAnswerResponse(String answer) {
    this.answer = answer;
  }

  public static GetAnswerResponse from(Answer answer) {
    return new GetAnswerResponse(answer.getContents());
  }
}
