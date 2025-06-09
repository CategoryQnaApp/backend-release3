package xyz.catequest.spring.domain.question.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.catequest.spring.domain.question.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  Optional<Question> findById(Long id);

  Question findByCategoryAndCategoryInId(String category, Long categoryInId);

  Long countByCategory(String category);
}
