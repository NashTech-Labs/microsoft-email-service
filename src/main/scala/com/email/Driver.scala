package com.email

import com.email.LoadConfig.{getConfigValue, loadConfig}
import com.typesafe.config.Config

object Driver extends App {

  private val config: Config = loadConfig()

  private val tenantId = getConfigValue(config, "tenantId")
  private val clientId = getConfigValue(config, "clientId")
  private val clientSecret = getConfigValue(config, "clientSecret")
  private val senderEmail = getConfigValue(config, "senderEmail")
  private val recipientEmail = getConfigValue(config, "recipientEmail")

  private val graphEmailSender = GraphEmailSender

  // Authenticate and send the email
  private val result = graphEmailSender.sendEmail(tenantId, clientId, clientSecret, senderEmail, recipientEmail)

  println(s"Message Received :$result")

}
