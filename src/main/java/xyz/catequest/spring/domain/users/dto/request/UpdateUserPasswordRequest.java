package xyz.catequest.spring.domain.users.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.Regexp.RegularExpression;

@Getter
@RequiredArgsConstructor
public class UpdateUserPasswordRequest {

  private final String oldPassword;

  @Pattern(regexp = RegularExpression.PASSWORDS_ROLE)
  private final String newPassword;
}
