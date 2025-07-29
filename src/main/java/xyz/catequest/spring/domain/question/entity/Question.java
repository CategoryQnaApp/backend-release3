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
import lombok.Setter;
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
@SQLDelete(sql = "UPDATE question SET deleted_at = current_timestamp WHERE id = ?")
public class Question extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter @Column(nullable = false)
  private String question;

  @Setter
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Category category;

  @Setter
  @Column(name = "category_in_id", nullable = false)
  private Long categoryInId;

  public Question(String question, Category category) {
    this.question = question;
    this.category = category;
  }

  public Question(String question, Category category, Long categoryInId) {
    this(question, category);
    this.categoryInId = categoryInId;
  }

  public static Question of(String question, Category category) {
    return new Question(question, category);
  }

  public static Question of(String question, Category category, Long categoryInId) {
    return new Question(question, category, categoryInId);
  }
}
