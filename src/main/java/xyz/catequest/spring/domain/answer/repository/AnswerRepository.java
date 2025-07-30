package xyz.catequest.spring.domain.answer.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.answer.entity.Answer;
import xyz.catequest.spring.domain.question.enums.Category;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
  Optional<Answer> findByIdAndUser_Id(Long id, Long userId);

  // Note: 나중에 QueryDsl 변경 생각해보기
  List<Answer> findByQuestion_IdAndUser_Id(Long questionId, Long userId);

  List<Answer> findByQuestion_CategoryAndUser_Id(Category category, Long userId);

  List<Answer> findByQuestion_CategoryAndQuestion_CategoryInIdAndUser_Id(
      Category category, Long questionId, Long userId);

  List<Answer> findByUser_Id(Long userId);
}
