package repositories

import database.DatabaseSchema
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

class FavouritesStudioRepositoryImpl extends FavouritesStudioRepository with DatabaseSchema {

  val db = Database.forConfig("mysqlProfile")

  override def find(userId: Long, studioId: Long): Future[FavouriteStudio] = {
    db.run((favouriteStudios.filter(fav => (fav.userId === userId && fav.studioId === studioId))).result.head)
  }

  override def create(favourite: FavouriteStudio) = {
    db.run(favouriteStudios += favourite)
  }

  override def delete(favourite: FavouriteStudio): Future[Int] = {
    db.run(favouriteStudios.filter(fav => (fav.userId === favourite.userId && fav.studioId === favourite.studioId)).delete)
  }

  override def exists(favourite: FavouriteStudio): Future[Boolean] = {
    db.run(favouriteStudios.filter(fav => (fav.userId === favourite.userId && fav.studioId === favourite.studioId)).exists.result)
  }

  override def list(userId: Long): Future[List[FavouriteStudio]] = {
    db.run(favouriteStudios.filter(_.userId === userId).to[List].result)
  }
}
