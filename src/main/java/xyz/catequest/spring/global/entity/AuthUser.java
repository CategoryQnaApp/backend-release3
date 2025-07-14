package xyz.catequest.spring.global.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * 작성자 : 문성준
 * 일시 : 2025.04.03 - v1
 * 사용자 정보 보관용
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class AuthUser {
    private final Long userId;
    private final String email;
    private final List<GrantedAuthority> authorities;

    public String getRole() {
        return authorities.get(0).getAuthority();
    }


    // todo : 정적 팩토리 메소드 추가하기 from
}
