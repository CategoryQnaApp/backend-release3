package xyz.catequest.spring.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Response<T> {

    static <T> Response<T> success(T data) {
        return new SuccessResponse<>(data);
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
        private final T data;

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