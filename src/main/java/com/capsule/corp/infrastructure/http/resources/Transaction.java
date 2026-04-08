package com.capsule.corp.infrastructure.http.resources;

import com.capsule.corp.infrastructure.http.resources.enums.EntryType;
import com.capsule.corp.infrastructure.http.resources.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

  UUID transactionId;

  UUID accountNumber;
  LocalDateTime timestamp;
  BigDecimal amount;
  String initiator;

  TransactionType transactionType;

  EntryType entryType;
}
