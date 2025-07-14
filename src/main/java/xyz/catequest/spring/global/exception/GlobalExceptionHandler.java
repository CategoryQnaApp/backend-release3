package xyz.catequest.spring.global.exception;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import xyz.catequest.spring.global.dto.CustomException;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.dto.ValidationException;
import xyz.catequest.spring.global.enums.ErrorMessage;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidRequestException.class)
  public Response<CustomException> invalidRequestExHandler(
      final InvalidRequestException ex, HttpServletResponse response) {
    HttpStatus status = ex.getErrorMessage().getStatus();
    log.error("[InvalidRequestException] name: {}, ", ex.getErrorMessage().name(), ex);

    response.setStatus(status.value());
    return Response.fail(status, new CustomException(ex.getErrorMessage()));
  }

  @ExceptionHandler(ServerException.class)
  public Response<CustomException> serverExHandler(
      final ServerException ex, HttpServletResponse response) {
    HttpStatus status = ex.getErrorMessage().getStatus();
    log.error("[ServerException] name: {}, ", ex.getErrorMessage().name(), ex);

    response.setStatus(status.value());
    return Response.fail(status, new CustomException(ex.getErrorMessage()));
  }

  @ExceptionHandler(NotFoundException.class)
  public Response<CustomException> notFoundExHandler(
      final NotFoundException ex, HttpServletResponse response) {
    HttpStatus status = ex.getErrorMessage().getStatus();
    log.error("[NotFoundException] name: {}, ", ex.getErrorMessage().name(), ex);

    response.setStatus(status.value());
    return Response.fail(status, new CustomException(ex.getErrorMessage()));
  }

  @ExceptionHandler(ForbiddenRequestException.class)
  public Response<CustomException> forbiddenExHandler(
      final ForbiddenRequestException ex, HttpServletResponse response) {
    HttpStatus status = ex.getErrorMessage().getStatus();
    log.error("[ForbiddenRequestException] name: {}", ex.getErrorMessage().name(), ex);

    response.setStatus(status.value());
    return Response.fail(status, new CustomException(ex.getErrorMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Response<List<ValidationException>> methodArgumentNotValidExHandler(
      final MethodArgumentNotValidException ex, HttpServletResponse response) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    List<ValidationException> validationList =
        fieldErrors.stream()
            .map(
                fieldError -> {
                  String code = fieldError.getCode();
                  String field = fieldError.getField();
                  String defaultMessage = fieldError.getDefaultMessage();
                  return new ValidationException(code, field, defaultMessage);
                })
            .toList();
    log.error("[MethodArgumentNotValidException]: ", ex);

    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    return Response.fail(HttpStatus.BAD_REQUEST, validationList);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public Response<CustomException> methodArgumentTypeMismatchExHandler(
      final MethodArgumentTypeMismatchException ex, HttpServletResponse response) {
    String expectedType =
        ex.getRequiredType() == null ? "Unknown" : ex.getRequiredType().getSimpleName();
    ErrorMessage typeMismatch = ErrorMessage.TYPE_MISMATCH;

    log.error(
        "[MethodArgumentTypeMismatchException] field: {}, expected: {}, value: {}, ",
        ex.getName(),
        expectedType,
        ex.getValue(),
        ex);

    response.setStatus(typeMismatch.getStatus().value());
    return Response.fail(typeMismatch.getStatus(), new CustomException(typeMismatch));
  }

  @ExceptionHandler(DataAccessException.class)
  public Response<CustomException> dataAccessExHandler(
      final DataAccessException ex, HttpServletResponse response) {
    int sqlErrorCode = -1;
    String sqlExceptionMessage = "";
    String sqlState = "";
    ErrorMessage errorMessage;

    Throwable rootCause = ex.getRootCause();
    if (rootCause instanceof SQLException sqlException) {
      sqlErrorCode = sqlException.getErrorCode();
      sqlState = sqlException.getSQLState();
      sqlExceptionMessage = sqlException.getMessage();
    }

    switch (sqlErrorCode) {
      case 1040 -> errorMessage = ErrorMessage.DB_TOO_MANY_CONNECTION_ERROR;
      case 1045 -> errorMessage = ErrorMessage.DB_AUTHENTICATE_ERROR;
      default -> errorMessage = ErrorMessage.DEFAULT_DB_ERROR;
    }

    log.error(
        "[DataAccessException] errorCode: {}, sqlState: {}, sqlExceptionMessage: {}, ",
        sqlErrorCode,
        sqlState,
        sqlExceptionMessage,
        ex);

    response.setStatus(errorMessage.getStatus().value());
    return Response.fail(errorMessage.getStatus(), new CustomException(errorMessage));
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public Response<CustomException> maxUploadSizeExceededExHandler(
      final MaxUploadSizeExceededException ex, HttpServletResponse response) {
    ErrorMessage errorMessage = ErrorMessage.FILE_SIZE_LIMIT_EXCEEDED;
    log.error("[MaxUploadSizeExceededException]: ", ex);

    response.setStatus(errorMessage.getStatus().value());
    return Response.fail(errorMessage.getStatus(), new CustomException(errorMessage));
  }

  @ExceptionHandler(SizeLimitExceededException.class)
  public Response<CustomException> sizeLimitExceededExHandler(
      final SizeLimitExceededException ex, HttpServletResponse response) {
    ErrorMessage errorMessage = ErrorMessage.SIZE_LIMIT_EXCEEDED;
    log.error("[SizeLimitExceededException] actualSize: {}, ", ex.getActualSize(), ex);

    response.setStatus(errorMessage.getStatus().value());
    return Response.fail(errorMessage.getStatus(), new CustomException(errorMessage));
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public Response<CustomException> noHandlerFoundExHandler(
      final NoHandlerFoundException ex, HttpServletResponse response) {
    ErrorMessage errorMessage = ErrorMessage.NO_HANDLER_FOUND;
    log.error("[NoHandlerFoundException]: ", ex);

    response.setStatus(ErrorMessage.NO_HANDLER_FOUND.getStatus().value());
    return Response.fail(errorMessage.getStatus(), new CustomException(errorMessage));
  }

  @ExceptionHandler(IOException.class)
  public Response<CustomException> ioExHandler(final IOException ex, HttpServletResponse response) {
    ErrorMessage errorMessage = ErrorMessage.FILE_IO_FAILED;
    log.error("[IOException]: ", ex);

    response.setStatus(errorMessage.getStatus().value());
    return Response.fail(errorMessage.getStatus(), new CustomException(errorMessage));
  }

  @ExceptionHandler(Exception.class)
  public Response<CustomException> exHandler(final Exception ex, HttpServletResponse response) {
    ErrorMessage errorMessage = ErrorMessage.ERROR;
    log.error("[Exception]: ", ex);

    response.setStatus(errorMessage.getStatus().value());
    return Response.fail(errorMessage.getStatus(), new CustomException(errorMessage));
  }
}
