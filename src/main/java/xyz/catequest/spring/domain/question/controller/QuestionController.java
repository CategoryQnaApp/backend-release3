package xyz.catequest.spring.domain.question.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.question.dto.request.CreateQuestionRequest;
import xyz.catequest.spring.domain.question.dto.request.UpdateQuestionRequest;
import xyz.catequest.spring.domain.question.dto.response.QuestionResponse;
import xyz.catequest.spring.domain.question.enums.Category;
import xyz.catequest.spring.domain.question.service.QuestionService;
import xyz.catequest.spring.global.dto.Response;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;

  @GetMapping("/v1/questions/{id}")
  public Response<QuestionResponse> getQuestion(@Positive @PathVariable Long id) {
    QuestionResponse response = questionService.getQuestion(id);
    return Response.success(response);
  }

  @GetMapping("/v1/questions/{category}/{categoryInId}")
  public Response<QuestionResponse> getQuestion(
      @NotNull @PathVariable Category category, @Positive @PathVariable Long categoryInId) {
    QuestionResponse response = questionService.getCategoryAndCategoryInId(category, categoryInId);
    return Response.success(response);
  }

  @GetMapping("/v1/questions/{category}")
  public Response<List<QuestionResponse>> getQuestions(@NotNull @PathVariable Category category) {
    List<QuestionResponse> responses = questionService.getQuestions(category);
    return Response.success(responses);
  }

  @PostMapping("/v1/questions")
  public Response<QuestionResponse> saveQuestion(
      @Valid @RequestBody CreateQuestionRequest createQuestionRequest) {
    QuestionResponse response =
        questionService.saveQuestion(
            createQuestionRequest.getQuestion(), createQuestionRequest.getCategory());
    return Response.created(response);
  }

  @PatchMapping("/v1/questions/{id}")
  public Response<Void> updateQuestion(
      @Positive @PathVariable Long id, @RequestBody UpdateQuestionRequest request) {
    questionService.updateQuestion(id, request);
    return Response.success();
  }

  @DeleteMapping("/v1/questions/{id}")
  public Response<Void> deleteQuestion(@Positive @PathVariable Long id) {
    questionService.deleteQuestion(id);
    return Response.success();
  }
}
