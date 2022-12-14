package io.pomatti.az.appconfig.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.spring.cloud.config.AppConfigurationRefresh;
import com.azure.spring.cloud.feature.manager.FeatureManager;

@RestController
public class ConfigController {

  Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  Config config;

  @Autowired
  FeatureManager featureManager;

  @Autowired(required = false)
  private AppConfigurationRefresh refresh;

  @GetMapping("/message")
  public String getMessage() {
    var value = config.getMessage();
    logger.info(value);
    return value;
  }

  @GetMapping("/refresh")
  public ResponseEntity<Object> refresh() throws Exception {
    if (refresh != null) {
      refresh.refreshConfigurations();
      return ResponseEntity.ok().build();
    }
    throw new Exception("It was not possible to refresh");
  }

  @GetMapping("/beta")
  public Boolean isBeta() {
    var beta = featureManager.isEnabledAsync("Beta").block();
    logger.info(beta.booleanValue() + "");
    return beta;
  }
}
