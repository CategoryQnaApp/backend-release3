package xyz.catequest.spring.domain.todo.dto.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.todo.entity.Todo;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class GetTodoResponse {
  private final LocalDate date;

  private final boolean all;

  private final boolean isDiary;

  private final boolean isAnswer;

  private final boolean isTalking;

  public static GetTodoResponse from(Todo todo) {
    return new GetTodoResponse(
        todo.getDate(), todo.isAll(), todo.isDiary(), todo.isAnswer(), todo.isTalking());
  }
}
