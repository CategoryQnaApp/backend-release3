package xyz.catequest.spring.domain.diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "creator_id"}))
@NoArgsConstructor
public class DiaryTag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "diary_id")
  private Diary diary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tag_id")
  private Tag tag;

  public DiaryTag(Diary diary, Tag tag) {
    this.diary = diary;
    this.tag = tag;
  }

  public static DiaryTag createDiaryTag(Diary diary, Tag tag) {
    return new DiaryTag(diary, tag);
  }
}
