package xyz.catequest.spring.global.dto;

import lombok.Getter;
import xyz.catequest.spring.global.enums.ErrorMessage;

@Getter
public class CustomException {

  private final String codeName;
  private final String message;

  public CustomException(ErrorMessage errorMessage) {
    this.codeName = errorMessage.name();
    this.message = errorMessage.getMessage();
  }
}
