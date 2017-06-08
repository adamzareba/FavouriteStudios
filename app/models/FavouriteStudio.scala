package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class FavouriteStudio(userId: Long, studioId: Long)

object FavouriteStudio {

  implicit val favouriteStudioWrites: Writes[FavouriteStudio] = (
    (JsPath \ "userId").write[Long] and
      (JsPath \ "studioId").write[Long]
    ) (unlift(FavouriteStudio.unapply))
}
