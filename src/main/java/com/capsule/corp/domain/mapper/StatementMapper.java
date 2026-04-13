package com.capsule.corp.domain.mapper;

import com.capsule.corp.domain.service.resources.StatementTransaction;
import com.capsule.corp.infrastructure.http.resources.Statement;
import com.capsule.corp.infrastructure.http.resources.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    imports = {UUID.class, LocalDateTime.class})
public interface StatementMapper {

  @Mapping(target = "date", source = "transaction.timestamp")
  @Mapping(target = "reference", source = "transaction.transactionId")
  @Mapping(target = "transactionType", source = "transaction.transactionType")
  @Mapping(target = "amount", source = "transaction.amount")
  List<StatementTransaction> mapStatementTransactions(List<Transaction> transaction);

  @Mapping(target = "statementId", expression = "java(UUID.randomUUID())")
  @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
  @Mapping(target = "statementFile", source = "statementFile")
  @Mapping(target = "extension", source = "extension")
  Statement mapStatement(byte[] statementFile, String extension);
}
