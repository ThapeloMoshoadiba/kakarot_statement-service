package com.capsule.corp.infrastructure.http.controller;

import com.capsule.corp.domain.service.StatementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statement-service/statements")
//@SecurityRequirement(name = "Bearer Authentication (JWT)")
@Tag(name = "Statement Service", description = "Handles Account Statements")
public class StatementController {

    // we need a JWT token to ensure that the person making calls on these endpoints is allowed to do so (e.g., an employee)

    private final StatementService statementService;

    @Operation(summary = "Request Statement")
    @GetMapping
    public String requestStatement(@RequestParam final String accountNumber, @RequestParam final String timePeriod) {
        return statementService.requestStatement(accountNumber, timePeriod);
    }

    @Operation(summary = "Get Statement")
    @GetMapping
    // Retrieve Account Statement (using link). Should this come via swagger? not sure
    public void getStatement(@RequestParam final String link) {
        statementService.getStatement(link);
    }


}
