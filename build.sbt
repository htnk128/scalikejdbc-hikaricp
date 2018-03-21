import scalariform.formatter.preferences._

name := "scalikejdbc-hikaricp"

scalaVersion := "2.12.4"

version      := "1.0.0"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature"
)

scalariformPreferences := scalariformPreferences.value
  .setPreference(CompactControlReadability, true)
  .setPreference(DanglingCloseParenthesis, Preserve)

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc" % "3.1.0",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.1.0",
  "com.zaxxer" % "HikariCP" % "2.7.8"
)
