package xyz.catequest.spring.domain.question.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.catequest.spring.domain.question.dto.response.QuestionResponse;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  public Question findQuestionById(Long id) {
    return questionRepository.findById(id).orElse(null);
  }

  public void createQuestion() {
    Question question = new Question("question", "건강", 1L);
    questionRepository.save(question);
  }

  public List<Question> findQuestions(String category) {
    return questionRepository.findByCategory(category);
  }

  public Question categoryAndCategoryInId(String category, Long categoryInId) {
    return questionRepository.findByCategoryAndCategoryInId(category, categoryInId)
                        .orElseThrow(() -> new RuntimeException("NullPointerException"));
  }

  public QuestionResponse saveQuestion(String question, String category) {
    Question saveQuesiton = new Question(question, category);

    Long categoryInId = questionRepository.countByCategory(category);
    saveQuesiton.setCategoryInId(categoryInId == 0 ? 1L : categoryInId + 1);

    Question saved = questionRepository.save(saveQuesiton);
    return QuestionResponse.from(saved);
  }
}
