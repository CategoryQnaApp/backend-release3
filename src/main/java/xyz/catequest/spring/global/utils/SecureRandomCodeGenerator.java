package xyz.catequest.spring.global.utils;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class SecureRandomCodeGenerator {
  private static final SecureRandom secureRandom = new SecureRandom();

  public static String generateRandomCode() {
    int randomInt = secureRandom.nextInt();
    randomInt = Math.abs(randomInt);
    randomInt %= 1000000;
    String randomCode = String.format("%06d",randomInt);
    return randomCode;
  }
}
