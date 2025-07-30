package xyz.catequest.spring.domain.checklist.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.checklist.dto.response.GetCheckListResponse;
import xyz.catequest.spring.domain.checklist.entity.CheckList;
import xyz.catequest.spring.domain.checklist.repository.CheckListRepository;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.domain.users.service.UserService;

@Service
@RequiredArgsConstructor
public class CheckListService {
  private final UserService userService;
  private final CheckListRepository checkListRepository;

  @Transactional
  public GetCheckListResponse getCheckList(Long userId) {
    CheckList today = getTodayCheckList(userId);
    return GetCheckListResponse.from(today);
  }

  // todo: 나중에 보상 추가하기
  @Transactional
  public void allDone(Long userId) {
    CheckList today = getTodayCheckList(userId);
    today.updateAll(true);
  }

  @Transactional
  public void diaryDone(Long userId) {
    CheckList today = getTodayCheckList(userId);
    today.updateDiary(true);
  }

  @Transactional
  public void answerDone(Long userId) {
    CheckList today = getTodayCheckList(userId);
    today.updateAnswer(true);
  }

  @Transactional
  public void talkingDone(Long userId) {
    CheckList today = getTodayCheckList(userId);
    today.updateTalking(true);
  }

  private CheckList getTodayCheckList(Long userId) {
    User user = userService.getUserEntity(userId);
    CheckList checkList =
        checkListRepository
            .findByTodayAndUser_Id(LocalDate.now().atStartOfDay(), user.getId())
            .orElse(CheckList.of(user));
    checkListRepository.save(checkList);
    return checkList;
  }
}
