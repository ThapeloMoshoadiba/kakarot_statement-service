package com.capsule.corp.domain.service;

import com.capsule.corp.common.exceptions.LinkExpiredException;
import com.capsule.corp.domain.mapper.StatementMapper;
import com.capsule.corp.domain.persistance.StatementRepository;
import com.capsule.corp.infrastructure.http.clients.accounts.AccountServiceClient;
import com.capsule.corp.infrastructure.http.clients.accounts.resources.AccountDetailedResponse;
import com.capsule.corp.infrastructure.http.clients.transactions.TransactionsServiceClient;
import com.capsule.corp.infrastructure.http.clients.transactions.resources.TransactionsResponse;
import com.capsule.corp.infrastructure.http.resources.Statement;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  public String requestStatement(final HttpServletRequest urlData, final UUID accountNumber) {

    String link = null;
    AccountDetailedResponse accountDetails = accountServiceClient.getAccount(accountNumber);

    if (accountDetails.isSuccess()) {
      TransactionsResponse transactionsDetails =
          transactionsServiceClient.getTransactions(accountNumber);
      byte[] statementFile = pdfService.generatePdfStatement(accountDetails, transactionsDetails);
      String extension = pdfService.generateExtension();

      Statement statement = statementMapper.mapStatement(statementFile, extension);
      statementRepository.save(statement);

      link =
          (urlData.getScheme() + "://" + urlData.getServerName() + ":" + urlData.getServerPort())
              .concat(extension);
    }

    return link;
  }

  public byte[] getStatement(final String extension) {

    byte[] statementFile = null;
    Optional<Statement> statement = statementRepository.findByExtension(extension);

    if (statement.isPresent()) {
      validateLink(statement.get());
      statementFile = statement.get().getStatementFile();
    }

    // if statement.isEmpty() should throw or return a 204

    return statementFile;
  }

  private void validateLink(Statement statement) {
    Duration duration = Duration.between(statement.getCreatedAt(), LocalDateTime.now());

    if (Math.abs(duration.toMinutes()) > 5) {
      statementRepository.delete(statement);
      log.error("Link is only valid for 5 minutes");
      throw new LinkExpiredException("Statement link has expired");
    }
  }
}
