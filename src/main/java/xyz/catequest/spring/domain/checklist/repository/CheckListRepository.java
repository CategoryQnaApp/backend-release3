package xyz.catequest.spring.domain.checklist.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.checklist.entity.CheckList;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
  Optional<CheckList> findByTodayAndUser_Id(LocalDateTime now, Long userId);
}
