package com.capsule.corp.domain.service;

import com.capsule.corp.domain.mapper.StatementMapper;
import com.capsule.corp.domain.service.resources.StatementTransaction;
import com.capsule.corp.infrastructure.http.clients.accounts.resources.AccountDetailedResponse;
import com.capsule.corp.infrastructure.http.clients.transactions.resources.TransactionsResponse;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfService {

  private final TemplateEngine templateEngine;
  private final StatementMapper statementMapper;

  public byte[] generatePdfStatement(
      final AccountDetailedResponse accountDetails,
      final TransactionsResponse transactionsResponse) {

    List<StatementTransaction> statementTransactions =
        statementMapper.mapStatementTransactions(transactionsResponse.getTransactions());

    Context context = new Context();
    context.setVariable(
        "accountHolder",
        accountDetails.getClientDetails().getFirstName()
            + " "
            + accountDetails.getClientDetails().getLastName());
    context.setVariable(
        "accountNumber", accountDetails.getAccounts().getFirst().getAccountNumber());
    context.setVariable("statementDate", LocalDate.now().toString());
    context.setVariable("transactions", statementTransactions);
    context.setVariable("balance", transactionsResponse.getBalance());

    String htmlContent = templateEngine.process("account-statement.html", context);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(htmlContent);
    renderer.layout();
    renderer.createPDF(outputStream);

    return outputStream.toByteArray();
  }

  public String generateExtension() {
    return "/".concat(UUID.randomUUID().toString().replace("-", ""));
  }
}
