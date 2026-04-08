package com.capsule.corp.infrastructure.http.resources;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statement")
public class Statement {

  @Id
  @Column(name = "statement_id", nullable = false, updatable = false)
  UUID statementId;

  LocalDateTime createdAt;
  byte[] statementFile;
  String extension;
}
