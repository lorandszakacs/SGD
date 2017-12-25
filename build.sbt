import sbt._
import sbt.Keys._

lazy val root = Project(
  "sg-downloader",
  base = file(".")
).settings(
  Settings.common
).aggregate(
  `sg-harvester`
)

lazy val `sg-harvester` = Project(
  "sg-harvester",
  base = file("./sg-harvester")
).settings(
  Settings.common ++
    Seq(
      mainClass in(Compile, run) in ThisBuild := Some("com.lorandszakacs.sg.app.Main"),
      suppressSbtShellNotification := true,
      fork in run := true,
      libraryDependencies ++= Seq(
        Dependencies.akkaActor,
        Dependencies.akkaHttp,

        Dependencies.nScalaJodaTime,
        Dependencies.scalaParserCombinators,

        Dependencies.logbackClassic,
        Dependencies.scalaLogging,

        Dependencies.scalaTest
      )
    )
).aggregate(
  `sg-core`,
  `sg-model-repo`,
  `util`
).dependsOn(
  `sg-model-repo`,
  `util`
)

lazy val `sg-model-repo` = Project(
  "sg-model-repo",
  base = file("./sg-model-repo")
).settings(
  Settings.common ++
    Seq(
      libraryDependencies ++= Seq(
        Dependencies.nScalaJodaTime,
        Dependencies.reactiveMongo,

        Dependencies.logbackClassic,
        Dependencies.scalaLogging,
        Dependencies.scalaTest
      )
    )
).aggregate(
  `sg-core`,
  `util`
).dependsOn(
  `sg-core`,
  `util`
)

lazy val `sg-core` = Project(
  "sg-core",
  base = file("./sg-core")
).settings(
  Settings.common ++
    Seq(
      libraryDependencies := Seq()
    )
)

lazy val `util` = Project(
  "util",
  base = file("./util")
).settings(
  Settings.common ++
    Seq(
      libraryDependencies ++= Seq(
        //required for package com.lorandszakacs.util.mongodb
        Dependencies.reactiveMongo,

        //required for package com.lorandszakacs.util.time
        Dependencies.nScalaJodaTime,

        //required for package com.lorandszakacs.util.logging
        Dependencies.logbackClassic,
        Dependencies.scalaLogging,

        //required for package com.lorandszakacs.util.html
        Dependencies.java.jsoup,

        Dependencies.scalaTest
      )
    )
)