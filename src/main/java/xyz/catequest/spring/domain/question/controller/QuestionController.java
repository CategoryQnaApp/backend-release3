package xyz.catequest.spring.domain.question.controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.service.QuestionService;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/123")
    public BigInteger questionMethod1(@RequestParam BigInteger num) {
        return questionService.questionServiceMethod(num);
    }

    @GetMapping("/1")
    public void questionMethod2(@RequestParam String que) {
        questionService.createQuestion(que);
    }

    @GetMapping("/")
    public List<Question> questionMethod3(@RequestParam Long id, Model model) {
        return questionService.findQuestionById(id);
    }
}
