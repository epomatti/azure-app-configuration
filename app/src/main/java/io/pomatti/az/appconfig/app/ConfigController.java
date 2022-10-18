package io.pomatti.az.appconfig.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.spring.cloud.config.AppConfigurationRefresh;

@RestController
public class ConfigController {

  @Autowired
  Config config;

  @Autowired(required = false)
  private AppConfigurationRefresh refresh;

  @GetMapping("/message")
  public String getMessage() {
    var value = config.getMessage();
    System.out.println(value);
    return value;
  }

  @GetMapping("/config")
  public ResponseEntity<Config> getConfig() {
    return ResponseEntity.ok(config);
  }

  @GetMapping("/refresh")
  public ResponseEntity<Config> refresh() throws Exception {
    if (refresh != null) {
      refresh.refreshConfigurations();
      return ResponseEntity.ok(config);
    }
    throw new Exception("It was not possible to refresh");
  }

}
