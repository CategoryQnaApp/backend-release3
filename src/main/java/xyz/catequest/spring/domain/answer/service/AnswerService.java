package xyz.catequest.spring.domain.answer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.answer.dto.response.GetAnswerResponse;
import xyz.catequest.spring.domain.answer.entity.Answer;
import xyz.catequest.spring.domain.answer.repository.AnswerRepository;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerRepository answerRepository;

	public void saveAnswer(Long questionId, String answer ) {
		// todo : question 추가하기
		Answer saveAnswer = new Answer(answer);
		answerRepository.save(saveAnswer);
	}

	public GetAnswerResponse getAnswer(Long answerId) {
		Answer findAnswer = answerRepository.findById(answerId).orElseThrow(
			() -> new RuntimeException("NotFound"));
		return GetAnswerResponse.from(findAnswer);
	}
}
