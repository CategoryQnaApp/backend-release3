package xyz.catequest.spring.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.InvalidRequestException;

/** 작성자 : 문성준 일시 : 2025.04.03 - v1 */
@Getter
@RequiredArgsConstructor
public enum UserRole implements GrantedAuthority {
  USER("ROLE_USER"),
  ADMIN("ROLE_ADMIN"),
  ;

  private final String roleName;

  @Override
  public String getAuthority() {
    return this.roleName;
  }

  public static UserRole of(String input) {
    for (UserRole role : values()) {
      if (role.name().equalsIgnoreCase(input)) {
        return role;
      }
    }
    throw new InvalidRequestException(ErrorMessage.INVALID_USER_ROLE);
  }
}
