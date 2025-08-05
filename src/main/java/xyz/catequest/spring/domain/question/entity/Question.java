package xyz.catequest.spring.domain.question.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import xyz.catequest.spring.domain.question.enums.Category;
import xyz.catequest.spring.global.entity.BaseEntity;

@Entity
@Getter
@Table(name = "questions")
@SQLRestriction("deleted_at IS NULL")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE questions SET deleted_at = current_timestamp WHERE id = ?")
public class Question extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(name = "category_in_id", nullable = false)
  private Long categoryInId;

  public Question(String content, Category category) {
    this.content = content;
    this.category = category;
  }

  public Question(String content, Category category, Long categoryInId) {
    this(content, category);
    this.categoryInId = categoryInId;
  }

  public static Question of(String content, Category category) {
    return new Question(content, category);
  }

  public static Question of(String content, Category category, Long categoryInId) {
    return new Question(content, category, categoryInId);
  }

  public void updateQuestion(String content) {
    this.content = content;
  }

  public void updateCategory(Category category) {
    this.category = category;
  }

  public void updateCategoryInId(Long categoryInId) {
    this.categoryInId = categoryInId;
  }
}
