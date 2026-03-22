package com.capsule.corp.domain.service;

import com.capsule.corp.domain.persistance.StatementRepository;
import com.capsule.corp.infrastructure.http.controller.clients.accounts.AccountServiceClient;
import com.capsule.corp.infrastructure.http.controller.clients.transactions.TransactionsServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementService {

    // private final StatementRepository statementRepository;
    private final AccountServiceClient accountServiceClient;
    private final TransactionsServiceClient transactionsServiceClient;
    private final StatementCleanupService cleanupService;

    public String requestStatement(final String accountNumber, final String timePeriod) {

        /*

            1. Make call to account-service (via accountServiceClient) to check if client's account is in OPEN status
                IF account is OPEN:
                    a. Make call to transaction-service (via transactionsServiceClient) to get transactions for given time period
                    b. Generate a PDF doc and Generate a temporary link to access that PDF
                    c. Save doc, link and set an expiresAt (for when the link should expire) in temp_statements table via statementRepository. (NB: This table should have a expiresAt column)
                    d. trigger cleanupService
                    e. Return the link
                IF account is CLOSED:
                    throw AccountClosedException
        */

        return null;
    }

    public void getStatement(final String link) {

        /*

            1. Check if link exists in temp_statements table
                IF it does:
                    return PDF
                IF not:
                    ~ link may have either been deleted or was never generated ~
                    throw NoLinkException (i.e., user should request statement again)

        */
    }

    public void pdfGenerator(final String transaction_data, final String balance_data) {
        /*

        Generate a pdf statement
        return pdf statement to requestStatement()

        */
    }

    public void linkGenerator() {
        /*

        Generate a link
        return link to to requestStatement()

        */
    }
}
