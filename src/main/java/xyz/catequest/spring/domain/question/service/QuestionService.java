package xyz.catequest.spring.domain.question.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  public List<Question> findQuestionById(Long id) {
    return questionRepository.findQuestionById(id);
  }

  //    public void createQuestion(String que) {
  //        Question question = new Question(que);
  //        questionRepository.save(question);
  //    }

  public List<Question> findAll(String category, Long category_in_id) {
    return questionRepository.findByCategoryAndCategory_in_id(category, category_in_id);
  }
}
