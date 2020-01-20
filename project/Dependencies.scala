import sbt._

object Dependencies {
  val http4sVersion = "0.21.0-M6"

  val zioVersion       = "1.0.0-RC17"
  val zioCatsVersion   = "2.0.0.0-RC8"
  val zioNioVersion    = "0.4.0"
  val zioMacrosVersion = "0.6.0"

  val fs2Version        = "2.0.0"
  val catsVersion       = "2.0.0"
  val catsEffectVersion = "2.0.0"
  val circeVersion      = "0.12.1"
  val sttpVersion       = "2.0.0-RC5"

  lazy val scalaTest  = "org.scalatest" %% "scalatest"   % "3.0.5"

  lazy val cats       = "org.typelevel" %% "cats-core"   % catsVersion
  lazy val catsEffect = "org.typelevel" %% "cats-effect" % catsEffectVersion
  lazy val catsLaws   = "org.typelevel" %% "cats-laws"   % "1.1.0"

  lazy val zio         = "dev.zio"       %% "zio"              % zioVersion
  lazy val zioStreams  = "dev.zio"       %% "zio-streams"      % zioVersion
  lazy val zioCats     = "dev.zio"       %% "zio-interop-cats" % zioCatsVersion

  lazy val fs2         = "co.fs2"        %% "fs2-io"           % fs2Version
  lazy val fastParse   = "com.lihaoyi"   %% "fastparse"        % "2.1.3"

  lazy val sttp        = "com.softwaremill.sttp.client" %% "core" % sttpVersion
  lazy val sttpZIO     = "com.softwaremill.sttp.client" %% "async-http-client-backend-zio" % sttpVersion
  lazy val sttpCirce   = "com.softwaremill.sttp.client" %% "circe" % sttpVersion

  lazy val circeCore    = "io.circe" %% "circe-core" % circeVersion
  lazy val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
  lazy val circeParse   = "io.circe" %% "circe-parser" % circeVersion

  lazy val zioTest         = "dev.zio" %% "zio-test"        % zioVersion
  lazy val zioTestSbt      = "dev.zio" %% "zio-test-sbt"    % zioVersion
  lazy val zioMacrosCore   = "dev.zio" %% "zio-macros-core" % zioMacrosVersion
  lazy val zioMacrosTest   = "dev.zio" %% "zio-macros-test" % zioMacrosVersion
}