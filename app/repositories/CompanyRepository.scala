package repositories

import javax.inject.Inject

import database.DatabaseSchema
import models.Company
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

trait CompanyRepository {

  def find(id: Long): Future[Company]

  def findAll: Future[List[Company]]

  def create(company: Company): Future[Long]

  def update(id: Long, company: Company): Future[Int]

  def delete(id: Long): Future[Int]
}

class CompanyRepositoryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends CompanyRepository with DatabaseSchema {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.driver.api._

  override def find(id: Long): Future[Company] = {
    db.run(companies.filter(_.id === id).result.head)
  }

  override def findAll: Future[List[Company]] = {
    db.run(companies.to[List].result)
  }

  override def create(company: Company): Future[Long] = {
    db.run(companies returning companies.map(_.id) += company)
  }

  override def update(id: Long, company: Company): Future[Int] = {
    val companyToUpdate: Company = company.copy(Some(id))
    db.run(companies.filter(_.id === id).update(companyToUpdate))
  }

  def delete(id: Long): Future[Int] = {
    db.run(companies.filter(_.id === id).delete)
  }
}