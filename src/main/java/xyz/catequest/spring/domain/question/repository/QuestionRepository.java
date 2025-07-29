package xyz.catequest.spring.domain.question.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.enums.Category;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  Optional<Question> findById(Long id);

  List<Question> findByCategory(String category);

  Optional<Question> findByCategoryAndCategoryInId(String category, Long categoryInId);

  Long countByCategory(Category category);

  Long question(String question);
}
