package models

import models.dao.FavouritesStudioDAO
import play.api.libs.functional.syntax._
import play.api.libs.json._

case class FavouriteStudio(userId: Long, studioId: Long)

object FavouriteStudio {

  implicit val favouriteStudioWrites: Writes[FavouriteStudio] = (
    (JsPath \ "userId").write[Long] and
      (JsPath \ "studioId").write[Long]
    ) (unlift(FavouriteStudio.unapply))

  def addFavourite(userId: Long, studioId: Long): FavouriteStudio = {
    val favourite = FavouriteStudio(userId, studioId)
    FavouritesStudioDAO.create(favourite)

    favourite
  }

  def delete(userId: Long, studioId: Long) = FavouritesStudioDAO.delete(FavouriteStudio(userId, studioId))

  def findAllByUser(userId: Long): List[FavouriteStudio] = FavouritesStudioDAO.index(userId)

  def find(userId: Long, studioId: Long): Option[FavouriteStudio] = {
    val favourite = FavouriteStudio(userId, studioId)

    if(FavouritesStudioDAO.exists(favourite))
      Some(favourite)
    else
      None
  }
}
