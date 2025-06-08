package xyz.catequest.spring.domain.question.controller;

import ch.qos.logback.core.model.Model;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
  public List<Question> questionMethod3(@RequestParam Long id, Model model) {
    return questionService.findQuestionById(id);
  }

  @GetMapping("/")
  public List<Question> questionMethod4(@RequestParam String category, Long category_in_id) {
    return questionService.findAll(category, category_in_id);
  }
}
