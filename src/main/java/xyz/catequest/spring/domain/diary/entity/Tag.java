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
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.users.entity.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "creator_id"}))
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private User creator;

  @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DiaryTag> diaryTags = new ArrayList<>();

  public Tag(String name, User creator) {
    this.name = name.replace("#", "");
    this.creator = creator;
  }

  public static Tag create(String name, User creator) {
    return new Tag(name, creator);
  }
}
