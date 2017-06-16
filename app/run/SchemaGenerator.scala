package run

import config.DatabaseOperator
import database.DatabaseSchema
import slick.driver.MySQLDriver.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object SchemaGenerator extends App with DatabaseSchema with DatabaseOperator {

  val db = Database.forConfig("mysqlProfile")

  private val future = createSchemaIfNotExists

  Await.ready(future, Duration.Inf)
}
