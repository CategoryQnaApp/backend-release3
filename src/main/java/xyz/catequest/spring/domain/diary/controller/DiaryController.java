package xyz.catequest.spring.domain.diary.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.diary.dto.request.DiaryRequest;
import xyz.catequest.spring.domain.diary.dto.request.TagRequest;
import xyz.catequest.spring.domain.diary.dto.response.DiaryResponse;
import xyz.catequest.spring.domain.diary.service.DiaryService;
import xyz.catequest.spring.domain.diary.service.DiaryTagService;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.entity.AuthUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiaryController {
  private final DiaryTagService diaryTagService;
  private final DiaryService diaryService;

  // 일기 내용 저장
  @PostMapping("/v1/diaries")
  public Response<DiaryResponse> saveDiary(
      @Valid @RequestBody DiaryRequest request, @AuthenticationPrincipal AuthUser user) {
    DiaryResponse response = diaryTagService.saveDiary(request, user.getUserId());
    return Response.created(response);
  }

  // 일기 단건 조회 with id
  @GetMapping("/v1/diaries/{diaryId}")
  public Response<DiaryResponse> getDiary(
      @Positive @PathVariable Long diaryId, @AuthenticationPrincipal AuthUser user) {
    DiaryResponse response = diaryService.getDiary(diaryId, user.getUserId());
    return Response.success(response);
  }

  // 일기 다건 조회 with all
  @GetMapping("/v1/diaries")
  public Response<List<DiaryResponse>> getAllDiaries(@AuthenticationPrincipal AuthUser user) {
    List<DiaryResponse> responses = diaryService.getDiaries(user.getUserId());
    return Response.success(responses);
  }

  // 일기 다건 조회 with hashtag
  @GetMapping("/v1/diaries/tags")
  public Response<List<DiaryResponse>> getDiariesWithTag(
      @NotBlank @RequestParam String tag, @AuthenticationPrincipal AuthUser user) {
    List<DiaryResponse> responses = diaryService.getDiariesByTag(tag, user.getUserId());
    return Response.success(responses);
  }

  // 작성한 일기 수정 ( 시간 제한 )
  @PutMapping("/v1/diaries/{diaryId}")
  public Response<DiaryResponse> updateDiary(
      @Positive @PathVariable Long diaryId,
      @Valid @RequestBody DiaryRequest request,
      @AuthenticationPrincipal AuthUser user) {
    DiaryResponse response = diaryTagService.updateDiary(diaryId, request, user.getUserId());
    return Response.success(response);
  }

  @PutMapping("/v1/diaries/{diaryId}/tags")
  public Response<Void> updateTags(
      @Positive @PathVariable Long diaryId,
      @Valid @RequestBody TagRequest request,
      @AuthenticationPrincipal AuthUser user) {
    diaryTagService.updateDiaryTag(diaryId, request.getTagList(), user.getUserId());
    return Response.success();
  }

  @PatchMapping("/v1/diaries/{diaryId}/tags/add")
  public Response<Void> addDiaryTag(
      @Positive @PathVariable Long diaryId,
      @Valid @RequestBody TagRequest request,
      @AuthenticationPrincipal AuthUser user) {
    diaryTagService.addTagToDiary(diaryId, request.getTagList(), user.getUserId());
    return Response.success();
  }

  @PatchMapping("/v1/diaries/{diaryId}/tags/minus")
  public Response<Void> minusDiaryTag(
      @Positive @PathVariable Long diaryId,
      @Valid @RequestBody TagRequest request,
      @AuthenticationPrincipal AuthUser user) {
    diaryTagService.removeTagFromDiary(diaryId, request.getTagList(), user.getUserId());
    return Response.success();
  }

  // 작성한 일기 삭제
  @DeleteMapping("/v1/diaries/{diaryId}")
  public Response<Void> deleteDiary(
      @Positive @PathVariable Long diaryId, @AuthenticationPrincipal AuthUser user) {
    diaryService.deleteDiary(diaryId, user.getUserId());
    return Response.success();
  }
}
