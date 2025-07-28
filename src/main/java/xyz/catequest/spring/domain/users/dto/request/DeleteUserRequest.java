package xyz.catequest.spring.domain.users.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.annotation.ValidPassword;

@Getter
@RequiredArgsConstructor
public class DeleteUserRequest {
  @ValidPassword private final String password;
}
