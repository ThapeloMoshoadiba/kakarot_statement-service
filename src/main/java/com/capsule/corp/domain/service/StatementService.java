package com.capsule.corp.domain.service;

import com.capsule.corp.common.exceptions.LinkExpiredException;
import com.capsule.corp.common.exceptions.StatementNotFoundException;
import com.capsule.corp.domain.mapper.StatementMapper;
import com.capsule.corp.domain.persistance.StatementRepository;
import com.capsule.corp.domain.service.helpers.PdfService;
import com.capsule.corp.infrastructure.http.clients.accounts.AccountServiceClient;
import com.capsule.corp.infrastructure.http.clients.transactions.TransactionsServiceClient;
import com.capsule.corp.infrastructure.http.clients.transactions.resources.TransactionsResponse;
import com.capsule.corp.infrastructure.http.controller.response.StatementResponse;
import com.capsule.corp.infrastructure.http.resources.Statement;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementService {
  private final PdfService pdfService;
  private final StatementMapper statementMapper;
  private final StatementRepository statementRepository;
  private final AccountServiceClient accountServiceClient;
  private final TransactionsServiceClient transactionsServiceClient;

  public ResponseEntity<StatementResponse> requestStatement(
      final HttpServletRequest urlData, final UUID accountNumber) {
      TransactionsResponse transactionsDetails = transactionsServiceClient.getTransactions(accountNumber);
      byte[] statementFile = pdfService.generatePdfStatement(accountServiceClient.getAccount(accountNumber), transactionsDetails);
      String extension = pdfService.generateExtension();

      Statement statement = statementMapper.mapStatement(statementFile, extension);
      statementRepository.save(statement);

      return ResponseEntity.ok(StatementResponse.builder().link(pdfService.generateLink(urlData, extension)).build());
  }

  public ResponseEntity<byte[]> getStatement(final String extension) {
    Optional<Statement> statement = statementRepository.findByExtension(extension);

    if (statement.isEmpty()) {
      throw new StatementNotFoundException("No Such Statement");
    }

    validateLink(statement.get());
    byte[] statementFile = statement.get().getStatementFile();

    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=statement.pdf").contentType(MediaType.APPLICATION_PDF).contentLength(statementFile.length).body(statementFile);
  }

  private void validateLink(final Statement statement) {
    Duration duration = Duration.between(statement.getCreatedAt(), LocalDateTime.now());

    if (Math.abs(duration.toMinutes()) > 5) {
      statementRepository.delete(statement);
      throw new LinkExpiredException("Statement link has expired");
    }
  }
}
