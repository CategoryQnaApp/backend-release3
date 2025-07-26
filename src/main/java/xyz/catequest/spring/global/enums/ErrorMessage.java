package xyz.catequest.spring.global.enums;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

  /* 4xx other */
  TYPE_MISMATCH(BAD_REQUEST, "올바른 값을 입력해주세요."),
  NOT_SUPPORTED_METHOD(BAD_REQUEST, "잘못된 메소드로 요청하셨습니다."),
  FORBIDDEN_USER(FORBIDDEN, "권한이 없습니다."),
  INVALID_USER_ROLE(BAD_REQUEST, "유효하지 않는 권한입니다."),
  NO_HANDLER_FOUND(NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
  FILE_SIZE_LIMIT_EXCEEDED(PAYLOAD_TOO_LARGE, "전송하려는 개별 파일의 크기가 너무 큽니다."),
  SIZE_LIMIT_EXCEEDED(PAYLOAD_TOO_LARGE, "전송하려는 모든 파일의 크기가 너무 큽니다."),

  /* 4xx jwt*/
  INVALID_JWT_SIGNATURE(BAD_REQUEST, "유효하지 않는 JWT 서명 입니다."),
  INVALID_REFRESH_TOKEN(BAD_REQUEST, "유효하지 않은 Refresh Token입니다."),
  EXPIRED_REFRESH_TOKEN(BAD_REQUEST, "만료된 Refresh Token입니다."),
  UNSUPPORTED_JWT_TOKEN(BAD_REQUEST, "지원되지 않는 JWT 토큰 입니다."),

  /* 4xx user */
  INVALID_EMAIL(BAD_REQUEST, "올바르지 않은 이메일 형식입니다."),
  NOT_FOUND_EMAIL(BAD_REQUEST, "인증부터 다시 시작해주세요."),
  DUPLICATED_EMAIL(BAD_REQUEST, "이미 회원가입된 이메일입니다."),
  INCORRECT_AUTH_NUMBER(BAD_REQUEST, "잘못된 인증번호입니다."),
  UNVERIFIED_EMAIL(BAD_REQUEST, "인증되지 않은 이메일입니다."),
  USER_NOT_FOUND(NOT_FOUND, "아이디가 없거나, 비밀번호가 틀렸습니다."),
  WRONG_PASSWORD(UNAUTHORIZED, "아이디가 없거나, 비밀번호가 틀렸습니다."),

  /* 5xx */
  INTERNAL_ERROR(INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
  OPENAPI_ERROR(INTERNAL_SERVER_ERROR, "외부 API 호출 중 오류가 발생했습니다."),
  ELASTICSEARCH_ERROR(INTERNAL_SERVER_ERROR, "검색 서비스 오류가 발생했습니다."),
  REDIS_ERROR(INTERNAL_SERVER_ERROR, "캐시 서비스 오류가 발생했습니다."),
  UNKNOWN_ERROR(INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생했습니다."),
  DB_AUTHENTICATE_ERROR(INTERNAL_SERVER_ERROR, "DB 인증에 실패했습니다."),
  DB_TOO_MANY_CONNECTION_ERROR(INTERNAL_SERVER_ERROR, "DB 연결이 너무 많습니다."),
  DEFAULT_DB_ERROR(INTERNAL_SERVER_ERROR, "데이터베이스 에러가 발생했습니다."),
  FILE_IO_FAILED(INTERNAL_SERVER_ERROR, "파일 입출력 중 에러가 발생했습니다."),
  INDEX_FAILED_ERROR(INTERNAL_SERVER_ERROR, "INDEX 작업 중 에러가 발생했습니다."),
  REINDEXING_IO_ERROR(INTERNAL_SERVER_ERROR, "REINDEX 작업 수행 중 에러가 발생했습니다."),
  AWS_S3_ERROR(INTERNAL_SERVER_ERROR, "AWS S3 업로드 중 에러가 발생했습니다."),
  ;

  private final HttpStatus status;
  private final String message;
}
