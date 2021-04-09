name := "scala-training"

version := "0.1"

scalaVersion := "2.13.5"


val awsSdkVersion        = "2.16.19"
val catsVersion          = "2.4.2"
val catsEffectVersion    = "2.3.3"
val catsMtlVersion       = "0.7.1"
val circeVersion         = "0.13.0"
val circeGenericExtra    = "0.13.0"
val doobieVersion        = "0.12.1"
val logbackVersion       = "1.2.3"
val mssqlJdbcVersion     = "8.4.1.jre11"
val scalacheckVersion    = "1.15.3"
val specs2Version        = "4.10.6"
val telemeterVersion     = "8.0.1"
val http4sVersion        = "0.21.20"
val kindProjectorVersion = "0.11.3"

val testLibraryDependencies = Seq(
  "org.scalacheck"         %% "scalacheck"           % scalacheckVersion % Test,
  "org.specs2"             %% "specs2-cats"          % specs2Version     % Test,
  "org.specs2"             %% "specs2-core"          % specs2Version     % Test,
  "org.specs2"             %% "specs2-matcher-extra" % specs2Version     % Test,
  "org.specs2"             %% "specs2-scalacheck"    % specs2Version     % Test,
  "org.tpolecat"           %% "doobie-scalatest"     % doobieVersion     % Test,
  "org.tpolecat"           %% "doobie-specs2"        % doobieVersion     % Test,
)


libraryDependencies ++= testLibraryDependencies ++ Seq(
  "org.typelevel"          %% "cats-core"            % catsVersion,
  "org.typelevel"          %% "cats-effect"          % catsEffectVersion,
)


