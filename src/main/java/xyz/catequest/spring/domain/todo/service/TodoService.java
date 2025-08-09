package xyz.catequest.spring.domain.todo.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.todo.dto.response.GetTodoResponse;
import xyz.catequest.spring.domain.todo.entity.Todo;
import xyz.catequest.spring.domain.todo.repository.TodoRepository;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.domain.users.service.UserService;

@Service
@RequiredArgsConstructor
public class TodoService {
  private final UserService userService;
  private final TodoRepository todoRepository;

  @Transactional
  public GetTodoResponse getTodo(Long userId) {
    Todo today = getTodoByTodayAndSave(userId);
    return GetTodoResponse.from(today);
  }

  @Transactional(readOnly = true)
  public GetTodoResponse getTodo(Long userId, LocalDate date) {
    Todo checkList = getTodoByDate(date, userId);
    return GetTodoResponse.from(checkList);
  }

  @Transactional(readOnly = true)
  public List<GetTodoResponse> getTodoList(Long userId, LocalDate start, LocalDate end) {
    return todoRepository.findByUser_IdAndDateBetween(userId, start, end).stream()
        .map(GetTodoResponse::from)
        .toList();
  }

  // todo: 나중에 보상 추가하기
  @Transactional
  public void allDone(Long userId) {
    Todo today = getTodoByTodayAndSave(userId);
    today.updateAll(true);
  }

  @Transactional
  public void diaryDone(Long userId) {
    Todo today = getTodoByTodayAndSave(userId);
    today.updateDiary(true);
  }

  @Transactional
  public void answerDone(Long userId) {
    Todo today = getTodoByTodayAndSave(userId);
    today.updateAnswer(true);
  }

  @Transactional
  public void talkingDone(Long userId) {
    Todo today = getTodoByTodayAndSave(userId);
    today.updateTalking(true);
  }

  private Todo getTodoByTodayAndSave(Long userId) {
    User user = userService.getUserEntity(userId);
    Todo checkList =
        todoRepository.findByDateAndUser_Id(LocalDate.now(), user.getId()).orElse(Todo.of(user));
    todoRepository.save(checkList);
    return checkList;
  }

  private Todo getTodoByDate(LocalDate date, Long userId) {
    return todoRepository.findByDateAndUser_Id(date, userId).orElse(null);
  }
}
