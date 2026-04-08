package com.capsule.corp.common.exceptions;

public class LinkExpiredException extends RuntimeException {
  public LinkExpiredException(String message) {
    super(message);
  }
}
