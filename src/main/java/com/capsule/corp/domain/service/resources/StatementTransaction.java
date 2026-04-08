package com.capsule.corp.domain.service.resources;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StatementTransaction {

  String date;
  String reference;
  String transactionType;
  BigDecimal amount;
}
