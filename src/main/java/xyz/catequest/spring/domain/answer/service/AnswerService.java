package xyz.catequest.spring.domain.answer.service;


import java.util.List;
import java.util.Optional;
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
    Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("NotFound"));
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
  public List<Answer> getAllAnswer() {
    return answerRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<Answer> getAnswerByQuestionId(Long questionId) {
    return answerRepository.findByQuestion_Id(questionId);
  }
}
