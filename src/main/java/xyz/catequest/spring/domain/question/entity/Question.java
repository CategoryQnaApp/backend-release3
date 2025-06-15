package xyz.catequest.spring.domain.question.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "questions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @Column private String question;

  @Setter
  @Column private String category;

  @Setter
  @Column(name = "category_in_id")
  private Long categoryInId;

  public Question(String question, String category) {
    this.question = question;
    this.category = category;
  }

  public Question(String question, String category, Long categoryInId) {
    this(question, category);
    this.categoryInId = categoryInId;
  }

}
