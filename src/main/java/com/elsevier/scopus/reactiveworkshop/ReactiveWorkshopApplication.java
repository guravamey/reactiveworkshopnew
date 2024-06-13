package com.elsevier.scopus.reactiveworkshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

/**
 * Springboot application main class
 */
@SpringBootApplication
@EnableReactiveFeignClients
public class ReactiveWorkshopApplication {

  
  public static void main(String[] args) {
    SpringApplication.run(ReactiveWorkshopApplication.class, args);
  }
}
