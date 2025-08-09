package xyz.catequest.spring.domain.todo.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.todo.dto.response.GetTodoResponse;
import xyz.catequest.spring.domain.todo.service.TodoService;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.entity.AuthUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
  private final TodoService todoService;

  @GetMapping("/v1/todo")
  public Response<GetTodoResponse> getTodayTodo(@AuthenticationPrincipal AuthUser user) {
    GetTodoResponse response = todoService.getTodo(user.getUserId());
    return Response.success(response);
  }

  @GetMapping("/v1/todo/date")
  public Response<GetTodoResponse> getDateTodo(
      @AuthenticationPrincipal AuthUser user, @RequestParam LocalDate date) {
    GetTodoResponse response = todoService.getTodo(user.getUserId(), date);
    return Response.success(response);
  }
}
