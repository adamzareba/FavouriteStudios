package config

import database.DatabaseSchema
import slick.driver.MySQLDriver.api._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.jdbc.meta.MTable

import scala.concurrent.Future
import scala.util.Success

trait DatabaseOperator {

  self: DatabaseSchema =>

  def db: Database

  def createSchemaIfNotExists: Future[Unit] = {
    db.run(MTable.getTables).flatMap(tables =>
      if (tables.isEmpty) {
        db.run(allSchemas.create).andThen {
          case Success(_) => println("Schema created\n")
        }
      } else {
        println("Schema already exists\n")
        Future.successful()
      }
    )
  }
}
