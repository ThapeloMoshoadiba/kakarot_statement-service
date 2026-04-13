package com.capsule.corp.infrastructure.http.resources;

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
public class Account {

  private String accountId;
  private LocalDateTime createdAt;
  private String cifNumber;
  private UUID accountNumber;
  private String accountStatus;
  private BigDecimal initialCreditAmount;
  private LocalDateTime blockedAt;
  private String reasonForBlock;
  private LocalDateTime unblockedAt;
  private String reasonForUnblock;
  private LocalDateTime closedAt;
  private String reasonForClose;
}
