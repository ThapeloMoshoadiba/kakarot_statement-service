package com.capsule.corp.infrastructure.http.resources;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class ClientDetails {

  private UUID clientId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String cifNumber;
  private String title;
  private String firstName;
  private String middleName;
  private String lastName;
  private String idNumber;
  private String gender;
  private LocalDate dateOfBirth;
  private String address;
  private String cellphoneNumber;
  private String email;
  private String credit;
  private String employmentStatus;
  private String sourceOfFunds;
  private BigDecimal verifiedAnnualIncome;
  private String clientStatus;
  private LocalDateTime blockedAt;
  private String reasonForBlock;
  private LocalDateTime unblockedAt;
  private String reasonForUnblock;
}
