package xyz.catequest.spring.global.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import xyz.catequest.spring.domain.user.entity.User;
import xyz.catequest.spring.domain.user.enums.UserRole;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.ServerException;

/** 작성자 : 문성준 일시 : 2025.04.03 - v1 사용자 정보 보관용 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class AuthUser {
  private final Long userId;
  private final List<GrantedAuthority> authorities;

  public String getRole() {
    if (authorities.isEmpty()) {
      throw new ServerException(ErrorMessage.UNKNOWN_ERROR);
    }
    return authorities.get(0).getAuthority();
  }

  public static AuthUser of(Long userId, UserRole role) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
    return new AuthUser(userId, grantedAuthorities);
  }

  public static AuthUser from(User user) {
    return AuthUser.of(user.getId(), user.getRole());
  }
}
