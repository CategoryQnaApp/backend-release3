package xyz.catequest.spring.domain.users.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserNicknameRequest {

  @Size(min = 1, max = 30)
  private final String nickname;
}
