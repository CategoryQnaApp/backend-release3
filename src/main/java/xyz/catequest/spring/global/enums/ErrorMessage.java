package xyz.catequest.spring.global.enums;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

  /* 4xx */
  TYPE_MISMATCH(BAD_REQUEST, "올바른 값을 입력해주세요."),
  FORBIDDEN_USER(FORBIDDEN, "권한이 없습니다."),

  NO_HANDLER_FOUND(NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),

  FILE_SIZE_LIMIT_EXCEEDED(PAYLOAD_TOO_LARGE, "전송하려는 개별 파일의 크기가 너무 큽니다."),
  SIZE_LIMIT_EXCEEDED(PAYLOAD_TOO_LARGE, "전송하려는 모든 파일의 크기가 너무 큽니다."),

  /* 5xx */
  INTERNAL_ERROR(INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
  OPENAPI_ERROR(INTERNAL_SERVER_ERROR, "OPENAPI ERROR"),
  ELASTICSEARCH_ERROR(INTERNAL_SERVER_ERROR, "ELASTICSEARCH ERROR"),
  REDIS_ERROR(INTERNAL_SERVER_ERROR, "CACHE ERROR"),
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
