package xyz.catequest.spring.domain.todo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  Optional<Todo> findByDateAndUser_Id(LocalDate date, Long userId);

  List<Todo> findByUser_IdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
