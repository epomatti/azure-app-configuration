# Azure App Configuration sandbox

Create the app configuration resource:

```sh
cd infra

terraform init
terraform apply -auto-approve
```

Set the connection string as parameter:

```sh
export APP_CONFIGURATION_CONNECTION_STRING='connection-string-of-your-app-configuration-store'
```

Build and run the application:

```sh
mvn clean package
mvn spring-boot:run
```

To run the application local values add `-Dspring-boot.run.profiles=dev`.


## Reference

- [App Configuration Java Spring App](https://learn.microsoft.com/en-us/azure/azure-app-configuration/quickstart-java-spring-app)
- [Microsoft Spring Boot App Configuration Docs](https://microsoft.github.io/spring-cloud-azure/docs/azure-app-configuration/2.8.0/reference/html/index.html)
- [GitHub Spring Boot Sample](https://github.com/Azure-Samples/azure-spring-boot-samples/tree/main/appconfiguration/azure-spring-cloud-starter-appconfiguration-config/azure-spring-cloud-starter-appconfiguration-config-sample)
