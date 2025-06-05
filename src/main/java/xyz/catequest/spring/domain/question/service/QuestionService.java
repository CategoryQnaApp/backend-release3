package xyz.catequest.spring.domain.question.service;

import java.math.BigInteger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  public BigInteger questionServiceMethod(BigInteger m) {
    return m.multiply(m.add(BigInteger.valueOf(1))).divide(BigInteger.valueOf(2));
  }

  public List<Question> findQuestionById(Long id) {
    return questionRepository.findQuestionById(id);
  }

  public void createQuestion(String que) {
    Question question = new Question(que);
    questionRepository.save(question);
  }
}
