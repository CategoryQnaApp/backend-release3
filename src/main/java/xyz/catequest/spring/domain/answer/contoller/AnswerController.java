package xyz.catequest.spring.domain.answer.contoller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.answer.dto.request.CreateAnswerRequest;
import xyz.catequest.spring.domain.answer.dto.response.CreateAnswerResponse;
import xyz.catequest.spring.domain.answer.dto.response.GetAnswerResponse;
import xyz.catequest.spring.domain.answer.entity.Answer;
import xyz.catequest.spring.domain.answer.service.AnswerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnswerController {

  private final AnswerService answerService;

//  답변 전달
  @PostMapping("/v1/questions/{questionId}/answers")
  public ResponseEntity<CreateAnswerResponse> saveAnswer(
      @PathVariable Long questionId, @RequestBody CreateAnswerRequest createAnswerRequest
      // todo : 인증 추가하기
      ) {
    answerService.saveAnswer(questionId, createAnswerRequest.getAnswer());

    return new ResponseEntity<>(CreateAnswerResponse.isOk(), HttpStatus.OK);
  }

//  답변 확인
  @GetMapping("/v1/questions/{questionId}/answers")
  public ResponseEntity<List<Answer>> getAnswerByQuestion(@PathVariable Long questionId) {
    return new ResponseEntity<>(answerService.getAnswerByQuestionId(questionId), HttpStatus.OK);
  }

//  답변 단건 조회
  @GetMapping("/v1/questions/answers/{answersId}")
  public ResponseEntity<GetAnswerResponse> getAnswers(@PathVariable Long answerId) {
    GetAnswerResponse answer = answerService.getAnswer(answerId);

    return new ResponseEntity<>(answer, HttpStatus.OK);
  }

//  답변 다건 조회
  @GetMapping("/v1/questions/answers")
  public ResponseEntity<List<Answer>> getAllAnswer() {
    return new ResponseEntity<>(answerService.getAllAnswer(), HttpStatus.OK);
  }
}
