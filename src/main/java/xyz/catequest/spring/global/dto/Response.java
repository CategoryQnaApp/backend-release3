package xyz.catequest.spring.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@JsonInclude(Include.NON_NULL)
public interface Response<T> {

  static <T> Response<T> success() {
    return new SuccessResponse<>(HttpStatus.OK);
  }

  static <T> Response<T> success(T data) {
    return new SuccessResponse<>(HttpStatus.OK, data);
  }

  static <T> Response<T> created() {
    return new SuccessResponse<>(HttpStatus.CREATED);
  }

  static <T> Response<T> created(T data) {
    return new SuccessResponse<>(HttpStatus.CREATED, data);
  }

  static <T> Response<T> noContent() {
    return new SuccessResponse<>(HttpStatus.NO_CONTENT);
  }

  static <T> Response<T> fail(HttpStatus status, T error) {
    return new ErrorResponse<>(status, error);
  }

  static <T> Response<List<T>> fail(HttpStatus status, List<T> error) {
    return new ErrorResponse<>(status, error);
  }

  T getData();

  T getError();

  @Getter
  @RequiredArgsConstructor
  class SuccessResponse<T> implements Response<T> {
    private final HttpStatus status;
    private T data;

    public SuccessResponse(HttpStatus status, T data) {
      this.status = status;
      this.data = data;
    }

    @Override
    public T getError() {
      return null;
    }
  }

  @Getter
  @RequiredArgsConstructor
  class ErrorResponse<T> implements Response<T> {

    private final HttpStatus status;
    private final T error;

    @Override
    public T getData() {
      return null;
    }
  }
}
