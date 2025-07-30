package xyz.catequest.spring.domain.answer.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.answer.dto.request.CreateAnswerRequest;
import xyz.catequest.spring.domain.answer.dto.request.UpdateAnswerRequest;
import xyz.catequest.spring.domain.answer.dto.response.GetAnswerResponse;
import xyz.catequest.spring.domain.answer.service.AnswerService;
import xyz.catequest.spring.domain.question.enums.Category;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.entity.AuthUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnswerController {
  private final AnswerService answerService;

  @PostMapping("/v1/questions/{questionId}/answers")
  public Response<Void> saveAnswer(
      @Positive @PathVariable Long questionId, @Valid @RequestBody CreateAnswerRequest request, @AuthenticationPrincipal AuthUser authuser
  ) {
    answerService.saveAnswer(questionId, request, authuser.getUserId());
    return Response.created();
  }

  @PostMapping("/v1/questions/answers/{answerId}")
  public Response<GetAnswerResponse> updateAnswer(
      @Positive @PathVariable Long answerId, @Valid @RequestBody UpdateAnswerRequest request, @AuthenticationPrincipal AuthUser authuser
  ) {
    GetAnswerResponse response = answerService.updateAnswer(answerId, request, authuser.getUserId());
    return Response.success(response);
  }

  @GetMapping("/v1/questions/answers/{answerId}")
  public Response<GetAnswerResponse> getAnswer(
      @Positive @PathVariable Long answerId, @AuthenticationPrincipal AuthUser authUser
  ) {
    GetAnswerResponse getAnswerResponse = answerService.getAnswer(answerId, authUser.getUserId());
    return Response.success(getAnswerResponse);
  }

  @GetMapping("/v1/questions/{questionId}/answers")
  public Response<List<GetAnswerResponse>> getAnswerWithQuestionId(
      @Positive @PathVariable Long questionId, @AuthenticationPrincipal AuthUser authUser
  ) {
    List<GetAnswerResponse> responses = answerService
        .getAnswersWithQuestionId(questionId, authUser.getUserId());
    return Response.success(responses);
  }

  @GetMapping("/v1/questions/{category}/answers")
  public Response<List<GetAnswerResponse>> getAnswersWithCategory(
      @NotNull @PathVariable Category category, @AuthenticationPrincipal AuthUser authUser
  ) {
    List<GetAnswerResponse> responses = answerService
        .getAnswersWithCategory(category, authUser.getUserId());
    return Response.success(responses);
  }

  @GetMapping("/v1/questions/{category}/{categoryInId}/answers")
  public Response<List<GetAnswerResponse>> getAnswersWithCategoryAndCategoryInId(
      @NotNull @PathVariable Category category, @PathVariable Long categoryInId, @AuthenticationPrincipal AuthUser authUser
  ) {
    List<GetAnswerResponse> responses = answerService.getAnswersWithCategoryAndCategoryInId(
        category, categoryInId, authUser.getUserId());
    return Response.success(responses);
  }

  @GetMapping("/v1/question/answers")
  public Response<List<GetAnswerResponse>> getAnswers(
      @AuthenticationPrincipal AuthUser authUser
  ) {
    List<GetAnswerResponse> responses = answerService.getAnswersWithUserId(
        authUser.getUserId());
    return Response.success(responses);
  }
}
