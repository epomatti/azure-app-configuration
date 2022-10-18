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

  sku                   = "free"
  public_network_access = "Enabled"

  purge_protection_enabled = false
}
