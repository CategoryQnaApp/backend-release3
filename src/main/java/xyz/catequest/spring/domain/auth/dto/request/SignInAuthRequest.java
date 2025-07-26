package xyz.catequest.spring.domain.auth.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.annotation.ValidEmail;
import xyz.catequest.spring.global.annotation.ValidPassword;

@Getter
@RequiredArgsConstructor
public class SignInAuthRequest {

  @ValidEmail
  private final String email;

  @ValidPassword
  private final String password;
}
