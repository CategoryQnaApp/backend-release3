package xyz.catequest.spring.global.entity;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.ServerException;

/** 작성자 : 문성준 일시 : 2025.04.03 - v1 사용자 정보 보관용 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class AuthUser {
  private final Long userId;
  private final String email;
  private final List<GrantedAuthority> authorities;

  public String getRole() {
    if( authorities.isEmpty() ) {
      throw new ServerException(ErrorMessage.UNKNOWN_ERROR);
    }
    return authorities.get(0).getAuthority();

  }

  // todo : 정적 팩토리 메소드 추가하기 from
}
