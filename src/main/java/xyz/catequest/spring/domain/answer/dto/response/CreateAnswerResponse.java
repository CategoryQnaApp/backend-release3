package xyz.catequest.spring.domain.answer.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnswerResponse {

  private Boolean answerCheck;

  public static CreateAnswerResponse isOk() {
    CreateAnswerResponse createAnswerResponse = new CreateAnswerResponse();
    createAnswerResponse.setAnswerCheck(true);
    return createAnswerResponse;
  }
}
