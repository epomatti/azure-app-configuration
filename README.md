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
