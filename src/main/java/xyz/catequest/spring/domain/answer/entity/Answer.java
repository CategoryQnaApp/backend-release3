package xyz.catequest.spring.domain.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.catequest.spring.domain.question.entity.Question;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ANSWERS")
public class Answer { // extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "contents")
  private String contents;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id", updatable = false)
//  @JsonIgnore
  private Question question;

  // @Column(name = "post_page")
  // private String postPage;
  //
  // @Column(name = "item_count")
  // private Long characterLimitItemCount;

  public Answer(String answer) {
    this.contents = answer;
  }
}
