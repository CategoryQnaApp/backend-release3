package xyz.catequest.spring.domain.auth.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.annotation.ValidEmail;

@Getter
@RequiredArgsConstructor
public class EmailRequest {
  @ValidEmail private final String email;
}
