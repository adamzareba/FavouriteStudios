package repositories

import database.DatabaseSchema
import models.Company
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future

trait CompanyRepository {

  def find(id: Long): Future[Company]

  def findAll: Future[List[Company]]

  def create(company: Company): Future[Long]

  def update(company: Company): Future[Int]

  def delete(id: Long): Future[Int]
}

class CompanyRepositoryImpl extends CompanyRepository with DatabaseSchema {

  val db = Database.forConfig("mysqlProfile")

  override def find(id: Long): Future[Company] = {
    db.run(companies.filter(_.id === id).result.head)
  }

  override def findAll: Future[List[Company]] = {
    db.run(companies.to[List].result)
  }

  override def create(company: Company): Future[Long] = {
    db.run(companies returning companies.map(_.id) += company)
  }

  override def update(company: Company): Future[Int] = {
    val companyToUpdate: Company = company.copy(company.id)
    db.run(companies.filter(_.id === company.id).update(companyToUpdate))
  }

  def delete(id: Long): Future[Int] = {
    db.run(companies.filter(_.id === id).delete)
  }
}