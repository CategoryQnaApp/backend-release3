package xyz.catequest.spring.domain.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.global.annotation.ValidPassword;

@Getter
@RequiredArgsConstructor
public class UpdateUserPasswordRequest {

  @NotBlank private final String oldPassword;

  @ValidPassword private final String newPassword;
}
