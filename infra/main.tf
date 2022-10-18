terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "3.27.0"
    }
  }
  backend "local" {
    path = ".workspace/terraform.tfstate"
  }
}

provider "azurerm" {
  features {
    resource_group {
      prevent_deletion_if_contains_resources = false
    }
  }
}

resource "random_integer" "affix" {
  min = 100
  max = 999
}

### Group ###

resource "azurerm_resource_group" "default" {
  name     = "rg-appconfig"
  location = var.location
}

### App Configuration ###

resource "azurerm_app_configuration" "appconf" {
  name                = "app-config-${random_integer.affix.result}"
  resource_group_name = azurerm_resource_group.default.name
  location            = azurerm_resource_group.default.location

  sku                      = "free"
  public_network_access    = "Enabled"
  purge_protection_enabled = false
}

### App Configuration Keys ###

data "azurerm_client_config" "current" {}

resource "azurerm_role_assignment" "appconf_dataowner" {
  scope                = azurerm_app_configuration.appconf.id
  role_definition_name = "App Configuration Data Owner"
  principal_id         = data.azurerm_client_config.current.object_id
}

resource "azurerm_app_configuration_key" "key1" {
  configuration_store_id = azurerm_app_configuration.appconf.id
  key                    = "concurrentProcesses"
  label                  = "Concurrent processes for the consumer"
  value                  = "1000"
  content_type           = "kv"
  locked                 = false

  lifecycle {
    ignore_changes = [
      value, locked
    ]
  }

  depends_on = [
    azurerm_role_assignment.appconf_dataowner
  ]
}

resource "azurerm_app_configuration_feature" "demo" {
  configuration_store_id = azurerm_app_configuration.appconf.id
  description            = "Checks if demo access is enabled in the environment."
  name                   = "demo"
  label                  = "demo"
  enabled                = true

  lifecycle {
    ignore_changes = [
      enabled
    ]
  }
}

### Output ###

output "primary_read_key" {
  value = azurerm_app_configuration.appconf.primary_read_key
}

output "primary_write_key" {
  value = azurerm_app_configuration.appconf.primary_write_key
}
