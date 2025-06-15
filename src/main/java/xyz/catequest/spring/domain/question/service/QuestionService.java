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
    return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("NullPointerException"));
  }
  @Transactional(readOnly = true)
  public List<QuestionResponse> findQuestions(String category) {
    // java 8 에서 사용된 stream 사용해서 Question -> QuestionResponse 로 만들어오기
    // 이것도 블로그 적어오셈
    return questionRepository.findByCategory(category)
        .stream()
        .map(QuestionResponse::from).toList();
  }
  @Transactional(readOnly = true)
  public QuestionResponse getCategoryAndCategoryInId(String category, Long categoryInId) {
    Question question = questionRepository
        .findByCategoryAndCategoryInId(category, categoryInId)
        .orElseThrow(() -> new RuntimeException("NullPointerException"));
    return QuestionResponse.from(question);
  }

  @Transactional
  public QuestionResponse saveQuestion(String question, String category) {
    Question saveQuestion = new Question(question, category);

    Long categoryInId = questionRepository.countByCategory(category);
    saveQuestion.setCategoryInId(categoryInId == 0 ? 1L : categoryInId + 1);

    Question saved = questionRepository.save(saveQuestion);
    return QuestionResponse.from(saved);
  }

  @Transactional
  public void updateQuestion(Long id, String question) {
    if(question.isEmpty()) {
      throw new RuntimeException("NullPointerException");
    }
    Question updateQuestion =  questionRepository.findById(id).orElseThrow(() -> new RuntimeException("NullPointerException"));
    updateQuestion.setQuestion(question);
    questionRepository.save(updateQuestion);
  }

  @Transactional
  public void updateCategory(Long id, String category) {
    if(category.isEmpty()) {
      throw new RuntimeException("NullPointerException");
    }
    Question updateQuestion =  questionRepository.findById(id).orElseThrow(() -> new RuntimeException("NullPointerException"));
    Long categoryInId = questionRepository.countByCategory(category);
    updateQuestion.setCategory(category);
    updateQuestion.setCategoryInId(categoryInId == 0 ? 1L : categoryInId + 1);
    questionRepository.save(updateQuestion);
  }

  @Transactional
  public void updateQuestionAndCategory(Long id, String question, String category) {
    if(question.isEmpty() || category.isEmpty()) {
      throw new RuntimeException("NullPointerException");
    }
    Question updateQuestion =  questionRepository.findById(id).orElseThrow(() -> new RuntimeException("NullPointerException"));
    Long categoryInId = questionRepository.countByCategory(category);
    updateQuestion.setQuestion(question);
    updateQuestion.setCategory(category);
    updateQuestion.setCategoryInId(categoryInId == 0 ? 1L : categoryInId + 1);
    questionRepository.save(updateQuestion);
  }

  @Transactional
  public void deleteQuestion(Long id) {
    questionRepository.deleteById(id);
  }
}
