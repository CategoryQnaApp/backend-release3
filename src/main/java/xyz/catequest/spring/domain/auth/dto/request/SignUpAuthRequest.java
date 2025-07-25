package xyz.catequest.spring.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.Regexp.RegularExpression;

@Getter
@RequiredArgsConstructor
public class SignUpAuthRequest {

  @Pattern(regexp = RegularExpression.EMAIL_ROLE)
  private final String email;

  @Pattern(regexp = RegularExpression.PASSWORDS_ROLE)
  private final String password;

  @NotBlank private final String nickname;
}
