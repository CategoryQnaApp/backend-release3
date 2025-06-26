package xyz.catequest.spring.domain.answer.service;


import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.catequest.spring.domain.answer.dto.response.GetAnswerResponse;
import xyz.catequest.spring.domain.answer.entity.Answer;
import xyz.catequest.spring.domain.answer.repository.AnswerRepository;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class AnswerService {

  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;

  public void saveAnswer(Long questionId, String answer) {
    // todo : question 추가하기
    Question question = questionRepository.findById(questionId).orElseThrow(
            () -> new RuntimeException("에러코드 이유가없ㅇ등ㄹ등"));
    Answer saveAnswer = new Answer(answer);
    saveAnswer.setQuestion(question);

    answerRepository.save(saveAnswer);
  }

  public GetAnswerResponse getAnswer(Long answerId) {
    Answer findAnswer =
        answerRepository.findById(answerId).orElseThrow(() -> new RuntimeException("NotFound"));
    return GetAnswerResponse.from(findAnswer);
  }
}
