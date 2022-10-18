package io.pomatti.az.appconfig.app;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "config")
@Data
public class Config {

  private Boolean beta;
  private String concurrentProcesses;

}
