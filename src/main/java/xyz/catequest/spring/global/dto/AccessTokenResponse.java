package xyz.catequest.spring.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class AccessTokenResponse {
  private final String accessToken;
}
