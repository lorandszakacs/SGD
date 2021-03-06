import sbt._
import sbt.Keys._

addCommandAlias("build", ";compile;test:compile")
addCommandAlias("rebuild", ";clean;update;compile;test:compile")

addCommandAlias("ci", ";rebuild;test")

addCommandAlias("mkJar", ";rebuild;sg-harvester / assembly")

lazy val root = Project(
  "sg-downloader",
  base = file("."),
).settings(commonSettings)
  .aggregate(`sg-harvester`)

lazy val `sg-harvester` = project
  .settings(commonSettings)
  .settings(
    Seq(
      mainClass in (Compile, run) in ThisBuild := Some("com.lorandszakacs.sg.app.Main"),
      mainClass in assembly                    := Some("com.lorandszakacs.sg.app.Main"),
      suppressSbtShellNotification             := true,
      fork in run                              := true,
      libraryDependencies ++= Seq(
        http4sDSL,
        http4sClient,
        parserCombinators,
        scalaTest,
      ),
    ),
  )
  .aggregate(
    `sg-core`,
    `sg-repo`,
    `util`,
  )
  .dependsOn(
    `sg-repo`,
    `util`,
  )

lazy val `sg-repo` = project
  .settings(commonSettings)
  .settings(
    Seq(
      libraryDependencies ++= Seq(
        scalaTest,
      ),
    ),
  )
  .aggregate(
    `sg-core`,
    `util`,
  )
  .dependsOn(
    `sg-core`,
    `util`,
  )

lazy val `sg-core` = project
  .settings(commonSettings)

lazy val `util` = project
  .settings(commonSettings)
  .settings(
    Seq(
      libraryDependencies ++= Seq(
        //required for package com.lorandszakacs.util.mongodb
        reactiveMongo,
        logbackClassic,
        iolog4s,
        pureharmEffects,
        fs2,
        //required for package com.lorandszakacs.util.html
        jsoup,
        scalaTest,
      ),
    ),
  )

//============================================================================================
//===================================== Library versions =====================================
//============================================================================================
lazy val mainScalaV        = "2.13.0"       //https://github.com/scala/scala/releases
lazy val pureharmV         = "0.0.2-M15"    //https://github.com/busymachines/pureharm/releases
lazy val fs2V              = "1.1.0-M1"     //https://github.com/functional-streams-for-scala/fs2/releases
lazy val reactiveMongoV    = "0.18.1"       //https://github.com/ReactiveMongo/ReactiveMongo/releases
lazy val log4catsV         = "0.4.0-M1"     //https://github.com/ChristopherDavenport/log4cats/releases
lazy val scalaPCV          = "1.1.2"        //https://github.com/scala/scala-parser-combinators/releases
lazy val http4sV           = "0.21.0-M1"    //https://github.com/http4s/http4s/releases
lazy val scalaTestV        = "3.1.0-SNAP13" //https://github.com/scalatest/scalatest/releases
lazy val typesafeConfigV   = "1.3.3"        //java — https://github.com/lightbend/config/releases
lazy val logbackV          = "1.2.3"        //java — https://github.com/qos-ch/logback/releases
lazy val jsoupV            = "1.8.1"        //java — https://github.com/jhy/jsoup/releases
lazy val betterMonadicForV = "0.3.0"        //https://github.com/oleg-py/better-monadic-for/releases

//============================================================================================
//=========================================== MISC ===========================================
//============================================================================================

lazy val pureharmEffects:   ModuleID = "com.busymachines"       %% "pureharm-effects-cats"    % pureharmV       withSources ()
lazy val fs2:               ModuleID = "co.fs2"                 %% "fs2-core"                 % fs2V            withSources ()
lazy val reactiveMongo:     ModuleID = "org.reactivemongo"      %% "reactivemongo"            % reactiveMongoV  withSources ()
lazy val iolog4s:           ModuleID = "io.chrisdavenport"      %% "log4cats-slf4j"           % log4catsV       withSources ()
lazy val parserCombinators: ModuleID = "org.scala-lang.modules" %% "scala-parser-combinators" % scalaPCV        withSources ()
lazy val typeSafeConfig:    ModuleID = "com.typesafe"           % "config"                    % typesafeConfigV withSources ()
lazy val logbackClassic:    ModuleID = "ch.qos.logback"         % "logback-classic"           % logbackV        withSources ()
lazy val jsoup:             ModuleID = "org.jsoup"              % "jsoup"                     % jsoupV          withSources ()

//============================================================================================
//================================= http://typelevel.org/scala/ ==============================
//========================================  typelevel ========================================
//============================================================================================

lazy val http4sDSL    = "org.http4s" %% "http4s-dsl"          % http4sV
lazy val http4sClient = "org.http4s" %% "http4s-blaze-client" % http4sV

//cats, cats-effect, brought in by pureharm

//============================================================================================
//=========================================  testing =========================================
//============================================================================================

lazy val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % scalaTestV % Test withSources ()

//============================================================================================
//=================================== COMMON-SETTINGS ========================================
//============================================================================================

def commonSettings: Seq[Setting[_]] =
  Seq(
    organization in ThisBuild   := "com.lorandszakacs",
    test in assembly            := {},
    assemblyJarName in assembly := s"${name.value}.jar",
    scalaVersion                := mainScalaV,
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % betterMonadicForV),
  ) ++ Settings.scalaCompilerFlags
