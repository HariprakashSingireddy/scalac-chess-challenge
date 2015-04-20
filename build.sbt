name := "scalac-chess-solver"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-deprecation", "-feature", "-target:jvm-1.7", "-unchecked",
  "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint", "–optimise")

javaOptions in run ++= Seq("-Xms4g", "-Xmx4g", "-XX:+UseParallelGC", "-XX:+UseParallelOldGC", "-XX:MaxNewSize=3g")

fork in run := true

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
