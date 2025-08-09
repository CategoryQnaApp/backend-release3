package xyz.catequest.spring.domain.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.users.entity.User;

@Entity
@NoArgsConstructor
// note: userId 와 Date당 한개의 데이터가 있어야함
// 그래서 userId 와 Date에 유니크 설정을 함
// todo : 블로그 작성하기 ( 제목 : 한개의 테이블 유니크 및 인덱스 설정하는 법 )
@Table(
    name = "todo",
    uniqueConstraints =
        @UniqueConstraint(
            name = "uk_todo_user_date",
            columnNames = {"user_id", "date"}),
    indexes = {@Index(name = "idx_todo_user_date", columnList = "user_id, date")})
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Getter
  @Column(nullable = false)
  private LocalDate date;

  private boolean all;

  private boolean diaryDone;

  private boolean answerDone;

  private boolean TalkingDone;

  public Todo(User user) {
    this.user = user;
    this.date = LocalDate.now();
    this.all = false;
    this.diaryDone = false;
    this.answerDone = false;
    this.TalkingDone = false;
  }

  public Todo(User user, LocalDate date) {
    this.user = user;
    this.date = date;
    this.all = false;
    this.diaryDone = false;
    this.answerDone = false;
    this.TalkingDone = false;
  }

  public static Todo of(User user) {
    return new Todo(user);
  }

  public boolean getAllDone() {
    return all;
  }

  public boolean getDiaryDone() {
    return diaryDone;
  }

  public boolean getAnswerDone() {
    return answerDone;
  }

  public boolean getTalkingDone() {
    return TalkingDone;
  }

  public void updateAll(boolean all) {
    this.all = all;
    this.diaryDone = all;
    this.answerDone = all;
    this.TalkingDone = all;
  }

  public void updateDiary(boolean diary) {
    this.diaryDone = diary;
    isAllDone();
  }

  public void updateAnswer(boolean answer) {
    this.answerDone = answer;
    isAllDone();
  }

  public void updateTalking(boolean talking) {
    this.TalkingDone = talking;
    isAllDone();
  }

  private void isAllDone() {
    this.all = (this.diaryDone && this.answerDone && this.TalkingDone);
  }
}
