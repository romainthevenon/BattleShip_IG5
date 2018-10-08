ThisBuild / scalaVersion := "2.12.6"
ThisBuild / organization := "com.battleShip"

lazy val battleShip = (project in file("."))
  .settings(
    name := "BattleShip_Project",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies ++= Seq("com.github.tototoshi" %% "scala-csv" % "1.3.5")
  )
