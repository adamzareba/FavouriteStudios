name := "FavouriteStudios"

version := "1.0"

lazy val `favouritestudios` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies += "com.typesafe.play" %% "anorm" % "2.5.3"

libraryDependencies += evolutions

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

libraryDependencies += "io.swagger" %% "swagger-play2" % "1.5.3"

libraryDependencies += "org.webjars" %% "webjars-play" % "2.5.0-4"

libraryDependencies += "org.webjars" % "swagger-ui" % "3.0.10"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  