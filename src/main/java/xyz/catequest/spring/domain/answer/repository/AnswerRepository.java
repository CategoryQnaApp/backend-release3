package xyz.catequest.spring.domain.answer.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.answer.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
  List<Answer> findByQuestion_Id(Long questionId);
}
