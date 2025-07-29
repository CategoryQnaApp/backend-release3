package xyz.catequest.spring.domain.auth.enums;

public enum EmailStatus {
  PENDING_VERIFICATION, // 인증 대기 (이메일, 휴대폰 등)
  VERIFIED, // 인증 완료
  ;

  public static boolean isVerified(EmailStatus emailStatus) {
    return emailStatus.equals(EmailStatus.VERIFIED);
  }
}
