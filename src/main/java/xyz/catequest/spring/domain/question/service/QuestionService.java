package xyz.catequest.spring.domain.question.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.question.dto.request.UpdateQuestionRequest;
import xyz.catequest.spring.domain.question.dto.response.QuestionResponse;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.enums.Category;
import xyz.catequest.spring.domain.question.repository.QuestionRepository;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  @Transactional(readOnly = true)
  public Question getQuestionEntity(Long id) {
    return questionRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_QUESTION));
  }

  @Transactional(readOnly = true)
  public QuestionResponse getQuestion(Long id) {
    Question question =
        questionRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_QUESTION));
    return QuestionResponse.from(question);
  }

  @Transactional(readOnly = true)
  public List<QuestionResponse> getQuestions(Category category) {
    return questionRepository.findByCategory(category).stream()
        .map(QuestionResponse::from)
        .toList();
  }

  @Transactional(readOnly = true)
  public QuestionResponse getCategoryAndCategoryInId(Category category, Long categoryInId) {
    Question question =
        questionRepository
            .findByCategoryAndCategoryInId(category, categoryInId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_QUESTION));
    return QuestionResponse.from(question);
  }

  @Transactional
  public QuestionResponse saveQuestion(String question, Category category) {
    Long categoryInId = questionRepository.countByCategory(category);
    Question saveQuestion =
        Question.of(question, category, categoryInId == 0 ? 1L : categoryInId + 1);
    Question saved = questionRepository.save(saveQuestion);
    return QuestionResponse.from(saved);
  }

  @Transactional
  public void updateQuestion(Long id, UpdateQuestionRequest request) {
    Question updateQuestion =
        questionRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_QUESTION));
    if (!request.questionIsNull()) {
      updateQuestion.updateQuestion(request.getQuestion());
    }
    if (!request.categoryIsNull()) {
      Long categoryInId = questionRepository.countByCategory(request.getCategory());
      updateQuestion.updateCategory(request.getCategory());
      updateQuestion.updateCategoryInId(categoryInId);
    }
    questionRepository.save(updateQuestion);
  }

  @Transactional
  public void deleteQuestion(Long id) {
    Question deleteQuestion =
        questionRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_QUESTION));
    questionRepository.delete(deleteQuestion);
  }
}
