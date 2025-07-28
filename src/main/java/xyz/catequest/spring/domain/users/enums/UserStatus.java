package xyz.catequest.spring.domain.users.enums;

public enum UserStatus {
  ACTIVE, // 활성 (정상적으로 서비스 이용 가능)
  INACTIVE, // 비활성 (일시적 사용 중지, 장기 미접속 등)
  SUSPENDED, // 정지 (약관 위반 등으로 인한 제재)
  DELETE// 탈퇴/비활성화 요청 (일정 기간 후 영구 삭제 예정)
}
