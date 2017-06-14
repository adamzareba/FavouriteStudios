package repositories

import javax.inject.Inject

import models.{Company, FavouriteStudio}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

trait FavouritesStudioRepository {

  def create(favourite: FavouriteStudio): Future[Long]

  def delete(id: Long): Future[Int]

  def exists(favourite: FavouriteStudio): Boolean

  def list(userId: Long): List[FavouriteStudio]
}

class FavouritesStudioRepositoryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends FavouritesStudioRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.driver.api._

  private[repositories] val FavouriteStudios = TableQuery[FavouriteStudioTable]

  override def create(favourite: FavouriteStudio): Future[Long] = {
    db.run(FavouriteStudios returning FavouriteStudios.map(_.userId) += favourite)
  }

  override def delete(id: Long): Future[Int] = {
    db.run(FavouriteStudios.filter(_.userId === id).delete)
  }

//  override def exists(favourite: FavouriteStudio): Boolean = {
//    database.withConnection { implicit connection =>
//      val result = SQL(
//        """
//          SELECT COUNT(*) as numMatches FROM favouriteStudio WHERE USER_ID = {userId} AND STUDIO_ID = {studioId}
//        """).on(
//        "userId" -> favourite.userId,
//        "studioId" -> favourite.studioId
//      ).apply().head
//
//      result[Int]("numMatches") != 0
//    }
//  }
//
//  override def list(userId: Long): List[FavouriteStudio] = {
//    database.withConnection { implicit connection =>
//      val results = SQL(
//        """
//          SELECT USER_ID, STUDIO_ID FROM favouriteStudio WHERE USER_ID = {userId}
//        """).on(
//        "userId" -> userId
//      ).apply()
//
//      results.map { row =>
//        FavouriteStudio(row[Long]("USER_ID"), row[Long]("STUDIO_ID"))
//      }.force.toList
//    }
//  }
  override def exists(favourite: FavouriteStudio): Boolean = ???

  override def list(userId: Long): List[FavouriteStudio] = ???

  private[repositories] class FavouriteStudioTable(tag: Tag) extends Table[FavouriteStudio](tag, "favouriteStudio") {

    def userId = column[Long]("userId")

    def studioId = column[Long]("studioId")

    def * = (userId, studioId) <> (FavouriteStudio.tupled, FavouriteStudio.unapply)

    def ? = (userId.?, studioId.?).shaped.<>({ r => import r._; _1.map(_ => FavouriteStudio.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))
  }
}
