package repositories

import javax.inject.Inject

import anorm.{Macro, RowParser, SQL}
import models.Company
import play.api.db.Database

trait CompanyRepository {

  def find(id: Long): Company

  def findAll: List[Company]

  def create(company: Company)

  def update(company: Company)

  def delete(id: Long)
}

class CompanyRepositoryImpl @Inject()(database: Database) extends CompanyRepository {

  implicit val parser: RowParser[Company] = Macro.namedParser[Company]

  override def find(id: Long): Company = {
    database.withConnection { implicit connection =>
      SQL(
        """
          SELECT * FROM company WHERE ID = {id}
        """).on(
        "id" -> id
      ).as(parser.single)
    }
  }

  override def findAll: List[Company] = {
    database.withConnection { implicit connection =>
      SQL(
        """
          SELECT * FROM company
        """).as(parser.*)
    }
  }

  override def create(company: Company)= {
    database.withConnection { implicit connection =>
      SQL(
        """
          INSERT IGNORE INTO company(NAME) VALUES ({name})
        """).on(
        "name" -> company.name
      ).executeInsert()
    }
  }

  override def update(company: Company): Unit = ???

  override def delete(id: Long): Unit = ???
}