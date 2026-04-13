package com.capsule.corp.infrastructure.http.resources;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  private String transactionId;
  private UUID accountNumber;
  private String timestamp;
  private BigDecimal amount;
  private String initiator;
  private String transactionType;
  private String entryType;
}
