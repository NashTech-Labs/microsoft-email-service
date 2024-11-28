import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
      name := "microsoft-email-service"
)

libraryDependencies ++= Seq(
"com.azure" % "azure-identity" % "1.6.1",
"com.microsoft.graph" % "microsoft-graph" % "5.80.0",
"com.typesafe" % "config" % "1.4.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

envFileName in ThisBuild := ".env-email-cred"
