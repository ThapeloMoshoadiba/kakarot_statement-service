package com.capsule.corp.domain.service;

import com.capsule.corp.domain.persistance.StatementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CleanupService {

  private final StatementRepository statementRepository;

  public void cleanup() {
    // we need a cleanup service that runs periodically to remove old statements
  }
}
