name := "FavouriteStudios"

version := "1.0"

lazy val `favouritestudios` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies += "com.typesafe.play" %% "anorm" % "2.5.0"

libraryDependencies += evolutions

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  