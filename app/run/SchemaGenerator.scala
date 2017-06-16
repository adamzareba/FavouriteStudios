package run

import config.DatabaseOperator
import database.DatabaseSchema
import slick.backend.DatabaseConfig
import slick.driver.MySQLDriver
import slick.driver.MySQLDriver.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object SchemaGenerator extends App with DatabaseSchema with DatabaseOperator {

  val dbConfig: DatabaseConfig[MySQLDriver] = DatabaseConfig.forConfig("slick.dbs.default")
  val db = dbConfig.db

  private val future = createSchemaIfNotExists

  Await.ready(future, Duration.Inf)
}
