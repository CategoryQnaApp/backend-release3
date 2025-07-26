package xyz.catequest.spring.domain.auth.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.annotation.ValidEmail;

@Getter
@RequiredArgsConstructor
public class EmailVerificationRequest {
  @ValidEmail
  private final String email;

  private final String verificationCode;
}
