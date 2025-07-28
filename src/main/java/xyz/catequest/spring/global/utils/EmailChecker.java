package xyz.catequest.spring.global.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailChecker {
  public static boolean isValid(String email) {
    if (email == null ) return false;

    // 추가 검사 (SonarQube-friendly 방식)
    if (email.length() > 320) return false; // 전체 길이 제한
    String[] parts = email.split("@");
    if (parts.length != 2) return false;
    if (parts[0].length() > 64 || parts[1].length() > 255) return false;
    if (parts[1].startsWith("-") || parts[1].endsWith("-")) return false;
    if (parts[1].contains("..")) return false; // 연속 점 방지

    return true;
  }
}
