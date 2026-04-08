package com.capsule.corp.infrastructure.http.controller;

import com.capsule.corp.domain.service.StatementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statement-service/statements")
// @SecurityRequirement(name = "Bearer Authentication (JWT)")
@Tag(name = "Statement Service", description = "Handles Account Statements")
public class StatementController {

  // we need a JWT token to ensure that the person making calls on these endpoints is allowed to do
  // so (e.g., an employee)

  private final StatementService statementService;

  @Operation(summary = "Request Statement")
  @GetMapping
  public String requestStatement(
      final HttpServletRequest urlData, @RequestParam final UUID accountNumber) {
    return statementService.requestStatement(urlData, accountNumber);
  }

  @Operation(summary = "Get Statement")
  @GetMapping("/{extension}")
  public byte[] getStatement(@PathVariable String extension) {
    return statementService.getStatement(extension);
  }
}
