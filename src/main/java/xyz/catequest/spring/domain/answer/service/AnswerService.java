package xyz.catequest.spring.domain.answer.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

  @Transactional
  public void saveAnswer(Long questionId, String answer) {
    // todo : question 추가하기
    Answer saveAnswer = new Answer(answer);
    Question question =
        questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("NotFound"));
    saveAnswer.setQuestion(question);
    answerRepository.save(saveAnswer);
  }

  @Transactional(readOnly = true)
  public GetAnswerResponse getAnswer(Long answerId) {
    Answer findAnswer =
        answerRepository.findById(answerId).orElseThrow(() -> new RuntimeException("NotFound"));
    return GetAnswerResponse.from(findAnswer);
  }

  @Transactional(readOnly = true)
  public List<GetAnswerResponse> getAnswers() {
    List<Answer> answers = answerRepository.findAll();
    List<GetAnswerResponse> answerResponses = new ArrayList<>();
    for (Answer answer : answers) {
      GetAnswerResponse dto = GetAnswerResponse.from(answer);
      answerResponses.add(dto);
    }
    return answerResponses;
  }

  @Transactional(readOnly = true)
  public List<GetAnswerResponse> getAnswerByQuestionId(Long questionId) {
    List<Answer> answers = answerRepository.findByQuestion_Id(questionId);
    List<GetAnswerResponse> answerResponses = new ArrayList<>();
    for (Answer answer : answers) {
      GetAnswerResponse dto = GetAnswerResponse.from(answer);
      answerResponses.add(dto);
    }
    return answerResponses;
  }
}
