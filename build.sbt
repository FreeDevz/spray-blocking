name := "spray-blocking"

version := "1.0-SNAPSHOT"

organization := "com.github.sam"

scalaVersion := "2.10.3"

scalacOptions ++= Seq(
  "-language:_",
  "-feature",
  "-unchecked",
  "-deprecation")

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray Repository" at "http://repo.spray.io/"
)

libraryDependencies ++= {
  val akkaVersion = "2.2.3"
  val sprayVersion = "1.2.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor"    % akkaVersion,
    "io.spray"           % "spray-can"     % sprayVersion,
    "io.spray"           % "spray-routing" % sprayVersion,
    "io.spray"           % "spray-testkit" % sprayVersion,
    "io.spray"           % "spray-client"  % sprayVersion,
    "org.scalatest"     %% "scalatest"     % "2.0" % "test"
  )
}

seq(Revolver.settings: _*)
