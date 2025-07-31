package xyz.catequest.spring.domain.diary.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.global.entity.BaseEntity;

@Getter
@Entity
@Table(name = "diarys")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String content;

  private String emoticons;

  private String emojiUrl;

  @Column(nullable = true)
  private String imageUrl;

  private LocalDateTime savedAt;

  @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DiaryTag> diaryTags = new ArrayList<>();

  public Diary(User user, String content, String emotions, LocalDateTime savedAt) {
    this.user = user;
    this.content = content;
    this.emoticons = emotions;
    this.savedAt = savedAt;
  }

  public Diary(
      User user,
      String content,
      String emotions,
      LocalDateTime savedTime,
      String emojiSrc,
      String imageSrc) {
    this(user, content, emotions, savedTime);
    this.emojiUrl = emojiSrc;
    this.imageUrl = imageSrc;
  }

  public static Diary of(User user, String content, String emotions, LocalDateTime savedTime) {
    return new Diary(user, content, emotions, savedTime);
  }

  public static Diary of(
      User user,
      String content,
      String emotions,
      LocalDateTime savedTime,
      String emojiSrc,
      String imageSrc) {
    return new Diary(user, content, emotions, savedTime, emojiSrc, imageSrc);
  }

  public void updateContent(String content) {
    this.content = content;
  }

  public void updateEmoticons(String emotions) {
    this.emoticons = emotions;
  }

  public void updateEmojiSrc(String emojiSrc) {
    this.emojiUrl = emojiSrc;
  }

  public void updateImageSrc(String imageSrc) {
    this.imageUrl = imageSrc;
  }

  public void updateSavedTime(LocalDateTime savedAt) {
    this.savedAt = savedAt;
  }
}
