ThisBuild / scalaVersion := "2.12.6"
ThisBuild / organization := "com.battleShip"

lazy val battleShip = (project in file("."))
  .settings(
    name := "BattleShip_Project",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  )