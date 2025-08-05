package xyz.catequest.spring.domain.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.users.entity.User;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "todo")
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private LocalDate date;

  private boolean all;

  private boolean isDiary;

  private boolean isAnswer;

  private boolean isTalking;

  public Todo(User user) {
    this.user = user;
    this.date = LocalDate.now();
    this.all = false;
    this.isDiary = false;
    this.isAnswer = false;
  }

  public Todo(User user, LocalDate date) {
    this.user = user;
    this.date = date;
    this.all = false;
    this.isDiary = false;
    this.isAnswer = false;
  }

  public static Todo of(User user) {
    return new Todo(user);
  }

  public void updateAll(boolean all) {
    this.all = all;
  }

  public void updateDiary(boolean diary) {
    this.isDiary = diary;
    allDone();
  }

  public void updateAnswer(boolean answer) {
    this.isAnswer = answer;
    allDone();
  }

  public void updateTalking(boolean talking) {
    this.isTalking = talking;
    allDone();
  }

  private void allDone() {
    if (this.isDiary && this.isAnswer && this.isTalking) {
      this.all = true;
    }
  }
}
