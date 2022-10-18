package io.pomatti.az.appconfig.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/config")
@SpringBootApplication
public class Application {

  Logger logger = LoggerFactory.getLogger(Application.class);

  @Value("${azure.servicebus.connectionstring}")
  private String connectionString;

  @Value("${azure.servicebus.prefetchCount}")
  private Boolean demo;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/")
  public ResponseEntity<Config> get() {
    Config config = new Config();

    return ResponseEntity.ok(config);
  }

}
