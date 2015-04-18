name := "scalac-chess-solver"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-deprecation", "-feature", "-target:jvm-1.7", "-unchecked",
  "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"