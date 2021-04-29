name := "Final Exam Spring 2021"

version := "0.1"

scalaVersion := "2.13.5"

scalacOptions += "-deprecation"

val scalaTestVersion = "3.1.1"
val logBackVersion = "1.2.3"
val slf4jVersion = "1.7.30"

scalacOptions in (Compile,doc) ++= Seq("-groups", "-implicits", "-deprecation")

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
