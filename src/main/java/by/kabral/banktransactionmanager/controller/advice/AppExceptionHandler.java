package by.kabral.banktransactionmanager.controller.advice;

import by.kabral.banktransactionmanager.dto.ErrorResponseDto;
import by.kabral.banktransactionmanager.exception.InvalidRequestDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static by.kabral.banktransactionmanager.util.Constant.*;

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
}
