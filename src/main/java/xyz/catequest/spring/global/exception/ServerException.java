package xyz.catequest.spring.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.enums.ErrorMessage;

@Getter
@RequiredArgsConstructor
public class ServerException extends RuntimeException {

	private final ErrorMessage errorMessage;
}
