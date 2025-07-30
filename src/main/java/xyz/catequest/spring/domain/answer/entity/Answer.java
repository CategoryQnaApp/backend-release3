package xyz.catequest.spring.domain.answer.entity;

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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import xyz.catequest.spring.domain.answer.dto.request.AnswerRequest;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.global.entity.BaseEntity;

@Getter
@Entity
@Table(name = "answers")
@NoArgsConstructor
@SQLRestriction("deleted_at IS NULL")
@SQLDelete(sql = "UPDATE answers SET deleted_at = current_timestamp WHERE id = ?")
public class Answer extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "contents")
  private String contents;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id", nullable = false)
  private Question question;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String envelope;

  private Long usedItemCount;

  public Answer(String contents, Question question, User user, String envelope) {
    this.contents = contents;
    this.question = question;
    this.user = user;
    this.envelope = envelope;
    this.usedItemCount = 0L;
  }

  public Answer(
      String contents, Question question, User user, String envelope, Long usedItemCount) {
    this(contents, question, user, envelope);
    this.usedItemCount = usedItemCount;
  }

  public static Answer of(String contents, Question question, User user, String envelope) {
    return new Answer(contents, question, user, envelope);
  }

  public static Answer from(AnswerRequest request, User user, Question question) {
    return new Answer(
        request.getContent(), question, user, request.getEnvelope(), request.getUsedItemCount());
  }

  public void updateContents(String newContents) {
    this.contents = newContents;
  }

  public void updateEnvelope(String envelope) {
    this.envelope = envelope;
  }

  public void updateUsedItemCount(Long usedItemCount) {
    this.usedItemCount = usedItemCount;
  }
}
