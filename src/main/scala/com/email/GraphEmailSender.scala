package com.email

import com.azure.identity.ClientSecretCredentialBuilder
import com.microsoft.graph.authentication.TokenCredentialAuthProvider
import com.microsoft.graph.models.{BodyType, EmailAddress, ItemBody, Message, Recipient, UserSendMailParameterSet}
import com.microsoft.graph.requests.GraphServiceClient
import org.slf4j.LoggerFactory

import scala.jdk.CollectionConverters.*

object GraphEmailSender {

  private val logger = LoggerFactory.getLogger("GraphEmailSender")

  def sendEmail(tenantId: String, clientId: String, clientSecret: String, senderEmail: String, recipientEmail: String): String = {
    // Build the credentials and authentication provider
    val credential = new ClientSecretCredentialBuilder()
      .tenantId(tenantId)
      .clientId(clientId)
      .clientSecret(clientSecret)
      .build()

    val authProvider = new TokenCredentialAuthProvider(List("https://graph.microsoft.com/.default").asJava, credential)

    // Initialize Graph Service Client
    val graphClient = GraphServiceClient
      .builder()
      .authenticationProvider(authProvider)
      .buildClient()

    // Create email message
    val emailMessage = new Message()
    emailMessage.subject = "Test Email from Microsoft Graph API"

    // Set up email body
    val body = new ItemBody()
    body.contentType = BodyType.TEXT
    body.content = "Hello, this is a test email sent from Scala using Microsoft Graph API."
    emailMessage.body = body

    // Define the recipient
    val toRecipient = new Recipient()
    val emailAddress = new EmailAddress()
    emailAddress.address = recipientEmail
    toRecipient.emailAddress = emailAddress
    emailMessage.toRecipients = List(toRecipient).asJava

    // Set up parameters for sending mail
    val sendMailParameterSet = UserSendMailParameterSet
      .newBuilder()
      .withMessage(emailMessage)
      .withSaveToSentItems(true)
      .build()

    // Send the email and handle potential errors
    try {
      graphClient
        .users(senderEmail)
        .sendMail(sendMailParameterSet)
        .buildRequest()
        .post()
      logger.info(s"Email sent successfully to $recipientEmail from $senderEmail")
      s"Email sent successfully to $recipientEmail from $senderEmail"
    } catch {
      case e: Exception =>
        logger.error(s"Error sending email from $senderEmail to $recipientEmail: ", e)
        s"Error sending email from $senderEmail to $recipientEmail: "
    }
  }


}
