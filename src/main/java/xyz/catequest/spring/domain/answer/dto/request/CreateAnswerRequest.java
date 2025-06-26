package xyz.catequest.spring.domain.answer.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAnswerRequest {

	@NotBlank
	@Size(min = 1)
	private final String answer;

	@NotBlank
	private final Long questionId;

	private final String envlope;

	private final Long characterLimitItemCount;
}
