package xyz.catequest.spring.domain.users.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.annotation.ValidPassword;

@Getter
@RequiredArgsConstructor
public class UpdateUserPasswordRequest {

  private final String oldPassword;

  @ValidPassword private final String newPassword;
}
