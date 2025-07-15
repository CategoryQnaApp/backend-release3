package xyz.catequest.spring.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationException {

  private final String code;
  private final String field;
  private final String message;
}
