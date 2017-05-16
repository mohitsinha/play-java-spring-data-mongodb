import com.typesafe.sbt.packager.docker._

name := """play-java-spring-data-mongodb-guice"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, JavaAppPackaging, DockerPlugin)

val cryptoSecret = Option(System.getProperty("cryptosecret")).getOrElse("571a034e-3182-11e7-93ae-92361f002671")
val configFile = Option(System.getProperty("env")).getOrElse("application")

scalaVersion := "2.11.8"

libraryDependencies += javaJdbc
libraryDependencies += cache
libraryDependencies += javaWs
libraryDependencies += "org.springframework.data" % "spring-data-mongodb" % "1.9.6.RELEASE"


// change to smaller base image
dockerBaseImage := "frolvlad/alpine-oraclejdk8:latest"
dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("FROM", _) => List(cmd, Cmd("RUN", "apk update && apk add bash"))
  case other => List(other)
}
// setting a maintainer which is used for all packaging types</pre>
maintainer := "Mohit Sinha"
dockerEntrypoint := Seq("bin/mintpro", "-Dplay.crypto.secret="+cryptoSecret, "-Dconfig.file=conf/"+configFile+".conf")

// exposing the play ports
dockerExposedPorts in Docker := Seq(9000, 9443)

// publish to repo
//dockerRepository := Some("quay.io/")
//dockerUpdateLatest := true

// run this with: docker run -p 9000:9000 <name>:<version>
