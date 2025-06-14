package xyz.catequest.spring.domain.question.controller;

import ch.qos.logback.core.model.Model;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.question.dto.request.CreateQuestionRequest;
import xyz.catequest.spring.domain.question.dto.response.QuestionResponse;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.service.QuestionService;

@RestController
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;

  //    @GetMapping("/1")
  //    public void questionMethod2(@RequestParam String que) {
  //        questionService.createQuestion(que);
  //    }

  @GetMapping("/12")
  public Question questionMethod3(@RequestParam Long id, Model model) {
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
  public ResponseEntity<List<Question>> selectQuestions(@RequestParam String category) {
    //    QuestionResponse questionResponse = (QuestionResponse)
    // questionService.findQuestions(category);
    //    return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    return new ResponseEntity<>(questionService.findQuestions(category), HttpStatus.OK);
  }

  @GetMapping("/api/v1/questions/1")
  public ResponseEntity<Question> selectQuestions(
      @RequestParam String category, @RequestParam Long categoryInId) {
    return new ResponseEntity<>(
        questionService.categoryAndCategoryInId(category, categoryInId), HttpStatus.OK);
  }
}
