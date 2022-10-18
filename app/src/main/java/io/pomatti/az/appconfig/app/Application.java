package io.pomatti.az.appconfig.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(Config.class)
@SpringBootApplication
public class Application {

  Logger logger = LoggerFactory.getLogger(Application.class);

  @Autowired
  Config config;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/config")
  public ResponseEntity<Config> get() {
    return ResponseEntity.ok(config);
  }

}
