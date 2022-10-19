package io.pomatti.az.appconfig.app;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Validated
@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class Config {

  @NotNull
  private String message;

  @NotNull
  @Min(7)
  private Integer value1;

}
