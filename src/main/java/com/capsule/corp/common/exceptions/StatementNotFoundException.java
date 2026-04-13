package com.capsule.corp.common.exceptions;

public class StatementNotFoundException extends RuntimeException {
  public StatementNotFoundException(final String message) {
    super(message);
  }
}
