name := """play-java-spring-data-mongodb-guice"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.8"

libraryDependencies += javaJdbc
libraryDependencies += cache
libraryDependencies += javaWs
libraryDependencies += "org.springframework.data" % "spring-data-mongodb" % "1.9.6.RELEASE"
