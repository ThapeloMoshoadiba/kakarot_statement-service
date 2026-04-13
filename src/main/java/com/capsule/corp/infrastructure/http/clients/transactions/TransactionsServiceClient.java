package com.capsule.corp.infrastructure.http.clients.transactions;

import com.capsule.corp.common.config.AppConfiguration;
import com.capsule.corp.infrastructure.http.clients.transactions.resources.TransactionsResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionsServiceClient {

  private final AppConfiguration.TransactionServiceConfig config;
  private final RestClient transactionServiceRestClient;

  public TransactionsResponse getTransactions(final UUID accountNumber) {

    ResponseEntity<TransactionsResponse> response =
        transactionServiceRestClient
            .method(HttpMethod.GET)
            .uri(
                UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                    .queryParam("accountNumber", accountNumber)
                    .toUriString())
            .contentType(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(new ParameterizedTypeReference<>() {});

    return response.getBody();
  }
}
