package xyz.catequest.spring.domain.diary.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.diary.entity.Diary;
import xyz.catequest.spring.domain.diary.entity.DiaryTag;
import xyz.catequest.spring.domain.diary.entity.Tag;

public interface DiaryTagRepository extends JpaRepository<DiaryTag, Long> {

  Boolean existsByDiaryAndTag(Diary diary, Tag tag);

  Optional<DiaryTag> findByDiaryAndTag(Diary diary, Tag tag);

  List<DiaryTag> findAllByTag(Tag tag);

  List<DiaryTag> findAllByDiary(Diary diary);

  void deleteByDiary(Diary diary);
}
