package xyz.catequest.spring.domain.answer.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.answer.entity.Answer;
import xyz.catequest.spring.domain.question.dto.response.QuestionResponse;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class GetAnswerResponse {

  private final String content;
  private final String envelope;
  private final Long usedItemCount;
  private final QuestionResponse question;

  public static GetAnswerResponse from(Answer answer) {
    return GetAnswerResponse.of(answer.getContents(), answer.getEnvelope(), answer.getUsedItemCount(), QuestionResponse.from(answer.getQuestion()));
  }
}
