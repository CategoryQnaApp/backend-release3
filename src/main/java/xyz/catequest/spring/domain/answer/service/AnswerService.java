package xyz.catequest.spring.domain.answer.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.answer.dto.request.AnswerRequest;
import xyz.catequest.spring.domain.answer.dto.response.GetAnswerResponse;
import xyz.catequest.spring.domain.answer.entity.Answer;
import xyz.catequest.spring.domain.answer.repository.AnswerRepository;
import xyz.catequest.spring.domain.question.entity.Question;
import xyz.catequest.spring.domain.question.enums.Category;
import xyz.catequest.spring.domain.question.service.QuestionService;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.domain.users.service.UserService;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.InvalidRequestException;
import xyz.catequest.spring.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class AnswerService {

  private final AnswerRepository answerRepository;

  private final QuestionService questionService;
  private final UserService userService;

  @Transactional
  public void saveAnswer(Long questionId, AnswerRequest request, Long userId) {
    User user = userService.getUserEntity(userId);
    Question question = questionService.getQuestionEntity(questionId);
    Answer saveAnswer = Answer.from(request, user, question);
    answerRepository.save(saveAnswer);
  }

  @Transactional
  public GetAnswerResponse updateAnswer(Long answerId, AnswerRequest request, Long userId) {
    Answer findAnswer =
        answerRepository
            .findByIdAndUser_Id(answerId, userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ANSWER));
    // Note: Answer 가 24시간이 지났다면 Exception
    Duration duration = Duration.between(findAnswer.getCreatedAt(), LocalDateTime.now());
    if (duration.toHours() >= 24) {
      throw new InvalidRequestException(ErrorMessage.ANSWER_EXPIRED);
    }
    findAnswer.updateContents(request.getContent());
    findAnswer.updateEnvelope(request.getEnvelope());
    findAnswer.updateUsedItemCount(request.getUsedItemCount());
    answerRepository.save(findAnswer);
    return GetAnswerResponse.from(findAnswer);
  }

  @Transactional(readOnly = true)
  public GetAnswerResponse getAnswer(Long answerId, Long userId) {
    Answer findAnswer =
        answerRepository
            .findByIdAndUser_Id(answerId, userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ANSWER));
    return GetAnswerResponse.from(findAnswer);
  }

  @Transactional(readOnly = true)
  public List<GetAnswerResponse> getAnswersWithQuestionId(Long questionId, Long userId) {
    List<Answer> answers = answerRepository.findByQuestion_IdAndUser_Id(questionId, userId);
    return answers.stream().map(GetAnswerResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public List<GetAnswerResponse> getAnswersWithCategory(Category category, Long userId) {
    List<Answer> answers = answerRepository.findByQuestion_CategoryAndUser_Id(category, userId);
    return answers.stream().map(GetAnswerResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public List<GetAnswerResponse> getAnswersWithCategoryAndCategoryInId(
      Category category, Long categoryInId, Long userId) {
    List<Answer> answers =
        answerRepository.findByQuestion_CategoryAndQuestion_CategoryInIdAndUser_Id(
            category, categoryInId, userId);
    return answers.stream().map(GetAnswerResponse::from).toList();
  }

  @Transactional(readOnly = true)
  public List<GetAnswerResponse> getAnswersWithUserId(Long userId) {
    List<Answer> answers = answerRepository.findByUser_Id(userId);
    return answers.stream().map(GetAnswerResponse::from).toList();
  }
}
