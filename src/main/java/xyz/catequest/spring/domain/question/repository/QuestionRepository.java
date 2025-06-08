package xyz.catequest.spring.domain.question.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.catequest.spring.domain.question.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
  List<Question> findQuestionById(Long id);

  List<Question> findByCategoryAndCategory_in_id(String category, Long category_in_id);
}
