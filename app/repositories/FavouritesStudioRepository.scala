package repositories

import models.FavouriteStudio
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future

trait FavouritesStudioRepository {

  def find(userId: Long, studioId: Long): Future[FavouriteStudio]

  def create(favourite: FavouriteStudio)

  def delete(favourite: FavouriteStudio): Future[Int]

  def exists(favourite: FavouriteStudio): Future[Boolean]

  def list(userId: Long): Future[List[FavouriteStudio]]
}

class FavouritesStudioRepositoryImpl extends FavouritesStudioRepository {

  val db = Database.forConfig("mysqlProfile")

  private[repositories] val FavouriteStudios = TableQuery[FavouriteStudioTable]

  override def find(userId: Long, studioId: Long): Future[FavouriteStudio] = {
    db.run((FavouriteStudios.filter(fav => (fav.userId === userId && fav.studioId === studioId))).result.head)
  }

  override def create(favourite: FavouriteStudio) = {
    db.run(FavouriteStudios += favourite)
  }

  override def delete(favourite: FavouriteStudio): Future[Int] = {
    db.run(FavouriteStudios.filter(fav => (fav.userId === favourite.userId && fav.studioId === favourite.studioId)).delete)
  }

  override def exists(favourite: FavouriteStudio): Future[Boolean] = {
    db.run(FavouriteStudios.filter(fav => (fav.userId === favourite.userId && fav.studioId === favourite.studioId)).exists.result)
  }

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
