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

  UUID clientId;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  String cifNumber;
  String title;
  String firstName;
  String middleName;
  String lastName;
  String idNumber;
  String gender;
  LocalDate dateOfBirth;
  String address;
  String cellphoneNumber;
  String email;
  String credit;
  String employmentStatus;
  String sourceOfFunds;
  BigDecimal verifiedAnnualIncome;
  String clientStatus;
  LocalDateTime blockedAt;
  String reasonForBlock;
  LocalDateTime unblockedAt;
  String reasonForUnblock;
}
