package xyz.catequest.spring.domain.checklist.dto.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.checklist.entity.CheckList;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class GetCheckListResponse {
  private final LocalDate today;

  private final boolean all;

  private final boolean isDiary;

  private final boolean isAnswer;

  private final boolean isTalking;

  public static GetCheckListResponse from(CheckList checkList) {
    return new GetCheckListResponse(
        checkList.getToday(),
        checkList.isAll(),
        checkList.isDiary(),
        checkList.isAnswer(),
        checkList.isTalking());
  }
}
