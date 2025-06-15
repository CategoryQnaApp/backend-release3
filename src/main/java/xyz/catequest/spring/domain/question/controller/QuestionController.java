package xyz.catequest.spring.domain.question.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.question.dto.request.CreateQuestionRequest;
import xyz.catequest.spring.domain.question.dto.request.UpdateQuestionRequest;
import xyz.catequest.spring.domain.question.dto.response.QuestionResponse;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.service.QuestionService;

@RestController
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;

  @GetMapping("/12")
  public Question questionMethod3(@RequestParam Long id) {
    return questionService.findQuestionById(id);
  }

  @PostMapping("/api/v1/question")
  public ResponseEntity<QuestionResponse> saveQuestion(
      @Valid @RequestBody CreateQuestionRequest createQuestionRequest) {
    QuestionResponse questionResponse =
        questionService.saveQuestion(
            createQuestionRequest.getQuestion(), createQuestionRequest.getCategory());
    return new ResponseEntity<>(questionResponse, HttpStatus.CREATED); // httpStatus 201 created 감
  }

  @GetMapping("/api/v1/questions")
  public ResponseEntity<List<QuestionResponse>> getQuestions(@RequestParam String category) {
    //    QuestionResponse questionResponse = (QuestionResponse)
    // questionService.findQuestions(category);
    //    return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    return new ResponseEntity<>(questionService.findQuestions(category), HttpStatus.OK);
  }

  @GetMapping("/api/v1/questions/1")
  public ResponseEntity<QuestionResponse> getQuestion(
      @RequestParam String category, @RequestParam Long categoryInId) {
    return new ResponseEntity<>(
        questionService.getCategoryAndCategoryInId(category, categoryInId), HttpStatus.OK);
  }

  @PatchMapping("/api/v1/question/{id}/{qnc}")
  public void updateQuestion(@PathVariable Long id, @PathVariable String qnc,
      @RequestBody UpdateQuestionRequest updateQuestionRequest) {
        if(qnc.equals("q")) {
          questionService.updateQuestion(id ,
              updateQuestionRequest.getQuestion());
        }
        else {
          questionService.updateCategory(id ,
              updateQuestionRequest.getCategory());
        }
  }

  @PutMapping("/api/v1/question/{id}")
  public void updateQuestionAndCategory(@PathVariable Long id,
      @RequestBody UpdateQuestionRequest updateQuestionRequest) {
    questionService.updateQuestionAndCategory(id ,
        updateQuestionRequest.getQuestion(), updateQuestionRequest.getCategory());
  }

  @DeleteMapping("/api/v1/question/{id}/del")
  public void deleteQuestion(@PathVariable Long id) {
    questionService.deleteQuestion(id);
  }
}
