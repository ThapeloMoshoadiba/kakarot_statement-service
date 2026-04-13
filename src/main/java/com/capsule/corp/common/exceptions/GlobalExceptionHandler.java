package com.capsule.corp.common.exceptions;

import com.capsule.corp.infrastructure.http.controller.GlobalErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(StatementNotFoundException.class)
  public ResponseEntity<?> handleStatementNotFoundException(final StatementNotFoundException ex) {
    log.error("StatementNotFoundException:", ex);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(GlobalErrorResponse.builder().reason(ex.getMessage()).build());
  }

  @ExceptionHandler(LinkExpiredException.class)
  public ResponseEntity<?> handleLinkExpiredException(final LinkExpiredException ex) {
    log.error("LinkExpiredException:", ex);
    return ResponseEntity.status(HttpStatus.GONE)
            .body(GlobalErrorResponse.builder().reason(ex.getMessage()).build());
  }

  @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<?> handleBadRequestException(final Exception ex) {
    log.error("BadRequestException:", ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(GlobalErrorResponse.builder().reason("Bad Request").build());
  }

  @ExceptionHandler({
    DataException.class,
    PSQLException.class,
    DataIntegrityViolationException.class
  })
  public ResponseEntity<?> handleDatabaseException(final Exception ex) {
    log.error("DatabaseException:", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(GlobalErrorResponse.builder().reason("Database Error").build());
  }

  @ExceptionHandler(IntegrationException.class)
  public ResponseEntity<?> handleIntegrationException(final IntegrationException ex) {
    log.error("IntegrationException:", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(GlobalErrorResponse.builder().reason(ex.getMessage()).build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGenericException(final Exception ex) {
    log.error("Exception:", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(GlobalErrorResponse.builder().reason(ex.getMessage()).build());
  }
}
