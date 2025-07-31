package xyz.catequest.spring.domain.diary.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.users.entity.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tags")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name; // '#공부' 등 (보통 '#' 없이 저장)

  @ManyToOne(fetch = FetchType.LAZY)
  private User creator;

  @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DiaryTag> diaryTags = new ArrayList<>();

  public Tag(String name, User creator, List<DiaryTag> diaryTags) {
    this.name = name;
    this.creator = creator;
    this.diaryTags = diaryTags;
  }

  public Tag(String name, User creator) {
    this.name = name;
    this.creator = creator;
  }

  public static Tag create(String name, User creator, List<DiaryTag> diaryTags) {
    return new Tag(name, creator, diaryTags);
  }

  public static Tag create(String name, User creator) {
    return new Tag(name, creator);
  }
}
