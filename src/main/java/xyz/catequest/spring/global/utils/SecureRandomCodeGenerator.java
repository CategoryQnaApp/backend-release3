package xyz.catequest.spring.global.utils;

import java.security.SecureRandom;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecureRandomCodeGenerator {
  private static final SecureRandom secureRandom = new SecureRandom();

  public static String generateRandomCode() {
    int randomInt = secureRandom.nextInt(1000000);
    return String.format("%06d", randomInt);
  }
}
