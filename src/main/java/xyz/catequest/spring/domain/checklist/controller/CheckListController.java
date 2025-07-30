package xyz.catequest.spring.domain.checklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.checklist.dto.response.GetCheckListResponse;
import xyz.catequest.spring.domain.checklist.service.CheckListService;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.entity.AuthUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CheckListController {
  private final CheckListService checkListService;

  @GetMapping("/v1/checklist")
  public Response<GetCheckListResponse> getCheckList(@AuthenticationPrincipal AuthUser authUser) {
    checkListService.getCheckList(authUser.getUserId());
    return Response.success();
  }
}
