package repositories

import javax.inject.Inject

import models.{Company, FavouriteStudio}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

trait FavouritesStudioRepository {

  def create(favourite: FavouriteStudio)

  def delete(favourite: FavouriteStudio): Future[Int]

  def exists(favourite: FavouriteStudio): Boolean

  def list(userId: Long): Future[List[FavouriteStudio]]
}

class FavouritesStudioRepositoryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends FavouritesStudioRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.driver.api._

  private[repositories] val FavouriteStudios = TableQuery[FavouriteStudioTable]

  override def create(favourite: FavouriteStudio) = {
    db.run(FavouriteStudios += favourite)
  }

  override def delete(favourite: FavouriteStudio): Future[Int] = {
    db.run(FavouriteStudios.filter(fav => (fav.userId === favourite.userId && fav.studioId === favourite.studioId)).delete)
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

  override def exists(favourite: FavouriteStudio): Boolean = ???

  override def list(userId: Long): Future[List[FavouriteStudio]] = {
    db.run(FavouriteStudios.filter(_.userId === userId).to[List].result)
  }

  private[repositories] class FavouriteStudioTable(tag: Tag) extends Table[FavouriteStudio](tag, "favouriteStudio") {

    def userId = column[Long]("USER_ID")

    def studioId = column[Long]("STUDIO_ID")

    def * = (userId, studioId) <> (FavouriteStudio.tupled, FavouriteStudio.unapply)

    def ? = (userId.?, studioId.?).shaped.<>({ r => import r._; _1.map(_ => FavouriteStudio.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))
  }
}
