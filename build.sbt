import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "Scala intro",
    libraryDependencies += scalaTest % Test
  )

lazy val docs = (project.in(file("intro-docs")))
  .settings(
    mdocOut := new java.io.File("output")
  )
  .dependsOn(root)
  .enablePlugins(MdocPlugin)