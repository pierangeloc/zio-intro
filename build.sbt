import Dependencies._

lazy val commonSettings = inThisBuild(
    Seq(
      scalaVersion := "2.12.8",
      addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
      addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
      addCompilerPlugin(("org.scalamacros" % "paradise"  % "2.1.1") cross CrossVersion.full),
    )
)

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .settings(
    organization := "io.tuliplogic",
    organizationName := "TulipLogic BV",
    startYear := Some(2020),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    name := "zio-intro",
    organization := "io.tuliplogic",
    scalaVersion := "2.12.6",
    libraryDependencies ++= Seq(
      zio,
      zioCats,
      zioStreams,
      fastParse,
      sttp,
      sttpZIO,
      sttpCirce,
      circeCore,
      circeGeneric,
      circeParse,
      zioMacrosCore,
      zioMacrosTest,
      scalaTest % Test
    )
  )
