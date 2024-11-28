package com.email

import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

import scala.util.{Failure, Success, Try}

object LoadConfig {

  private val logger = LoggerFactory.getLogger("LoadConfig")

  def loadConfig(): Config = {
    try {
      ConfigFactory.load() // Load application.conf
    } catch {
      case exception: Exception =>
        logger.error("Error loading configuration: ", exception)
        throw exception
    }
  }

   def getConfigValue(config: Config, key: String): String = {
    // Return the configuration value or throw an exception if missing
    Try(config.getString(key)) match {
      case Success(value) => value
      case Failure(_) =>
        logger.error(s"Configuration key '$key' not found")
        throw new IllegalArgumentException(s"Missing configuration key: $key")
    }
  }

}
