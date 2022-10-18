package io.pomatti.az.appconfig.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "config")
// @Data
public class Config {

  private String message;

  public String getMessage() {
      return message;
  }

  public void setMessage(String message) {
      this.message = message;
  }

}
