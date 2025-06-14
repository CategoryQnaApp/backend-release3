package xyz.catequest.spring.domain.question.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.question.dto.response.QuestionResponse;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  @Transactional(readOnly = true)
  public Question findQuestionById(Long id) {
    // 여기 예외처리 해오셈
    // Optional.orElseThrow 사용
    return questionRepository.findById(id).orElse(null);
  }

  public List<Question> findQuestions(String category) {
    // java 8 에서 사용된 stream 사용해서 Question -> QuestionResponse 로 만들어오기
    // 이것도 블로그 적어오셈
    return questionRepository.findByCategory(category);
  }

  public Question categoryAndCategoryInId(String category, Long categoryInId) {
    return questionRepository
        .findByCategoryAndCategoryInId(category, categoryInId)
        .orElseThrow(() -> new RuntimeException("NullPointerException"));
  }

  @Transactional
  public QuestionResponse saveQuestion(String question, String category) {
    Question saveQuestion = new Question(question, category);

    Long categoryInId = questionRepository.countByCategory(category);
    saveQuestion.setCategoryInId(categoryInId == 0 ? 1L : categoryInId + 1);

    Question saved = questionRepository.save(saveQuestion);
    return QuestionResponse.from(saved);
  }
}
