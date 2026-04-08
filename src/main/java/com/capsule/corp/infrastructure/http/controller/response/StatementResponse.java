package com.capsule.corp.infrastructure.http.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StatementResponse {
  String link;
  boolean success;
}
