package com.capsule.corp.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfiguration {

  @Data
  @Component
  @ConfigurationProperties(prefix = "app.integration.account-service")
  public static class AccountServiceConfig extends IntegrationConfig {
    // private String someEndpoint;
  }

  @Data
  @Component
  @ConfigurationProperties(prefix = "app.integration.transaction-service")
  public static class TransactionServiceConfig extends IntegrationConfig {
    // private String someEndpoint;
  }
}
