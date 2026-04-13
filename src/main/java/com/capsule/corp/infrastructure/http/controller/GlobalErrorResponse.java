package com.capsule.corp.infrastructure.http.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GlobalErrorResponse {

  @Builder.Default private boolean success = false;
  private String reason;
}
