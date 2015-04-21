name := "scalac-chess-solver"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-feature", "-target:jvm-1.8", "-unchecked",
  "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint", "–optimise")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

javaOptions in run ++= Seq("-Xms8g", "-Xmx10g", "-XX:+UseParallelGC", "-XX:+UseParallelOldGC")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}

fork in run := true

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.storm-enroute" %% "scalameter" % "0.6" % "test")

testFrameworks += new TestFramework(
  "org.scalameter.ScalaMeterFramework")

logBuffered := false

parallelExecution in Test := false