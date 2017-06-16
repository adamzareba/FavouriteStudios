package run

import com.typesafe.config.ConfigFactory
import config.DatabaseOperator
import database.DatabaseSchema
import slick.driver.MySQLDriver.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object SchemaGenerator extends App with DatabaseSchema with DatabaseOperator {

  val config = ConfigFactory.load()
  val db = Database.forConfig("slick.dbs.default.db", config)

  private val future = createSchemaIfNotExists

  Await.ready(future, Duration.Inf)
}
