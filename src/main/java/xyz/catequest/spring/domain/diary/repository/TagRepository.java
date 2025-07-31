package xyz.catequest.spring.domain.diary.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.diary.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
  Optional<Tag> findByNameAndCreator_Id(String tagName, Long userId);

  List<Tag> findByCreator_Id(Long userId);

  List<Tag> findAllByNameInAndCreator_Id(List<String> tagNames, Long userId);

  boolean existsByNameAndCreator_Id(String tagName, Long userId);
}
