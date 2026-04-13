package com.capsule.corp.common.exceptions;

public class LinkExpiredException extends RuntimeException {
  public LinkExpiredException(final String message) {
    super(message);
  }
}
