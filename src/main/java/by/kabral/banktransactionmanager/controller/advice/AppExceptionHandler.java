package by.kabral.banktransactionmanager.controller.advice;

import by.kabral.banktransactionmanager.dto.ErrorResponseDto;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static by.kabral.banktransactionmanager.util.Constant.*;
import static by.kabral.banktransactionmanager.util.Message.*;

@ControllerAdvice
public class AppExceptionHandler {

  @ExceptionHandler(value = {
          InvalidRequestDataException.class
  })
  public ResponseEntity<ErrorResponseDto> defaultMessageExceptionHandler(Exception exception) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto.builder()
                    .message(exception.getMessage())
                    .timestamp(ZonedDateTime.now(ZoneId.of(UTC_ZONE_NAME)).toLocalDateTime())
                    .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
    StringBuilder errorMessage = new StringBuilder();

    exception.getBindingResult()
            .getAllErrors()
            .forEach(error -> errorMessage.append(error.getDefaultMessage()).append(" "));

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto.builder()
                    .message(errorMessage.toString().trim())
                    .timestamp(ZonedDateTime.now(ZoneId.of(UTC_ZONE_NAME)).toLocalDateTime())
                    .build());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponseDto> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
    return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(ErrorResponseDto.builder()
                    .message(String.format(METHOD_NOT_ALLOWED, exception.getMessage()))
                    .timestamp(ZonedDateTime.now(ZoneId.of(UTC_ZONE_NAME)).toLocalDateTime())
                    .build());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponseDto> methodArgumentTypeMismatchException() {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto.builder()
                    .message(REQUEST_PARAMETER_IS_INVALID)
                    .timestamp(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime())
                    .build());
  }
}
