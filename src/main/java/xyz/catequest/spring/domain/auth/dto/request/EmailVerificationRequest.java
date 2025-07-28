package xyz.catequest.spring.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.annotation.ValidEmail;

@Getter
@RequiredArgsConstructor
public class EmailVerificationRequest {
  @ValidEmail private final String email;

  @NotBlank(message = "인증 코드는 필수 입니다.")
  private final String verificationCode;
}
