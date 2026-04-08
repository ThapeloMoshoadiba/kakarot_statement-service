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

  String transactionId;
  UUID accountNumber;
  String timestamp;
  BigDecimal amount;
  String initiator;
  String transactionType;
  String entryType;
}
