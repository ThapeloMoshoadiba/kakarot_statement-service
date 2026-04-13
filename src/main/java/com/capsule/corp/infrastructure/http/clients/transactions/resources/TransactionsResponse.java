package com.capsule.corp.infrastructure.http.clients.transactions.resources;

import com.capsule.corp.infrastructure.http.resources.Transaction;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionsResponse {

  private List<Transaction> transactions;
  private BigDecimal balance;
  private boolean success;
}
