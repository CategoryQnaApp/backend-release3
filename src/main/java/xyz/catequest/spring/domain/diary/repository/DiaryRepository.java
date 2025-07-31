package xyz.catequest.spring.domain.diary.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.catequest.spring.domain.diary.entity.Diary;
import xyz.catequest.spring.domain.diary.projection.DiaryFlatProjection;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
  List<Diary> findByUser_Id(Long userId);

  Optional<Diary> findByIdAndUser_id(Long id, Long userId);

  @Query(
      "SELECT d FROM Diary d "
          + "LEFT JOIN FETCH d.diaryTags dt "
          + "LEFT JOIN FETCH dt.tag "
          + "WHERE d.id = :diaryId AND d.user.id = :userId")
  Optional<Diary> findByIdWithTags(@Param("diaryId") Long diaryId, @Param("userId") Long userId);

  @Query(
      """
SELECT d.id as diaryId,
       d.content as content,
       d.imageUrl as imageUrl,
       d.emoticons as emoticons,
       t.name as tagName,
       d.createdAt as savedAt
FROM Diary d
LEFT JOIN d.diaryTags dt
LEFT JOIN dt.tag t
WHERE d.user.id = :userId
""")
  List<DiaryFlatProjection> findDiariesWithTagsByUserId(@Param("userId") Long userId);

  @Query(
      """
SELECT d.id as diaryId,
       d.content as content,
       d.imageUrl as imageUrl,
       d.emoticons as emoticons,
       t.name as tagName,
       d.createdAt as savedAt
FROM Diary d
LEFT JOIN d.diaryTags dt
LEFT JOIN dt.tag t
WHERE d.user.id = :userId AND
      t.name = :tagName
""")
  List<DiaryFlatProjection> findDiariesWithTagsByTagNameAndUserId(
      @Param("tagName") String tagName, @Param("userId") Long userId);
}
