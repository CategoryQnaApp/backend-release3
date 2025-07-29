package xyz.catequest.spring.domain.auth.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class SignAuthResponse {
  private final String accessToken;
  private final String refreshToken;
}
