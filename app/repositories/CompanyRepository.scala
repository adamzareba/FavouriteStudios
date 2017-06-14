package repositories

import javax.inject.Inject

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

class CompanyRepositoryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends CompanyRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.driver.api._

  private[repositories] val Companies = TableQuery[CompanyTable]

  override def find(id: Long): Future[Company] = {
    db.run(Companies.filter(_.id === id).result.head)
  }

  override def findAll: Future[List[Company]] = {
    db.run(Companies.to[List].result)
  }

  override def create(company: Company): Future[Long] = {
    db.run(Companies returning Companies.map(_.id) += company)
  }

  override def update(id: Long, company: Company): Future[Int] = {
    val companyToUpdate: Company = company.copy(Some(id))
    db.run(Companies.filter(_.id === id).update(companyToUpdate))
  }

  def delete(id: Long): Future[Int] = {
    db.run(Companies.filter(_.id === id).delete)
  }

  private[repositories] class CompanyTable(tag: Tag) extends Table[Company](tag, "company") {

    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)

    def name = column[String]("NAME")

    def * = (id.?, name) <> (Company.tupled, Company.unapply)
  }
}