package com.capsule.corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor")
public class StatementServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(StatementServiceApplication.class, args);
  }
}
