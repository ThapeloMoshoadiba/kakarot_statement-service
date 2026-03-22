package com.capsule.corp.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementCleanupService {

    // once this is triggered, it starts counting for (lets say) 2 minutes. Once the two minutes is done, it deletes the given pdf from the temp_statements table

}
