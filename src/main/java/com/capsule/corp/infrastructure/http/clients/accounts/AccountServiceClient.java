package com.capsule.corp.infrastructure.http.clients.accounts;

import com.capsule.corp.config.AppConfiguration;
import com.capsule.corp.infrastructure.http.clients.accounts.resources.AccountDetailedResponse;
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
public class AccountServiceClient {

  private final AppConfiguration.AccountServiceConfig config;
  private final RestClient accountServiceRestClient;

  public AccountDetailedResponse getAccount(final UUID accountNumber) {
    try {
      ResponseEntity<AccountDetailedResponse> response =
          accountServiceRestClient
              .method(HttpMethod.GET)
              .uri(
                  UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                      .queryParam("accountNumber", accountNumber)
                      .toUriString())
              .contentType(MediaType.APPLICATION_JSON)
              .retrieve()
              .toEntity(new ParameterizedTypeReference<>() {});

      return response.getBody();
    } catch (Exception e) {
      log.error("Error Getting Account: [{}] [{}]", accountNumber, e.getMessage());
    }
    return AccountDetailedResponse.builder().success(false).build();
  }
}
