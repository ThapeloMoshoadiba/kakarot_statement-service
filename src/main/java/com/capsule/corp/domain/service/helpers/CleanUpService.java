package com.capsule.corp.domain.service.helpers;

import static com.capsule.corp.infrastructure.http.resources.Constants.MIDNIGHT;

import com.capsule.corp.domain.persistance.StatementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CleanUpService {

  private final StatementRepository statementRepository;

  @Scheduled(cron = MIDNIGHT)
  public void clearStatementRepository() {
    statementRepository.deleteAll();
  }
}
