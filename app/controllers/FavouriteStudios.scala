package controllers

import models.FavouriteStudio
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object FavouriteStudios extends Controller {

  def add(userId: Long, studioId: Long) = Action {
    val favourite = FavouriteStudio.addFavourite(userId, studioId)
    Ok(Json.obj("result" -> favourite))
  }

  def remove(userId: Long, studioId: Long) = Action {
    FavouriteStudio.delete(userId, studioId)

    Ok(Json.obj("result" -> Json.obj()))
  }

  def find(userId: Long, studioId: Long) = Action {
    val optionFavourite = FavouriteStudio.find(userId, studioId)

    optionFavourite match {
      case None => NotFound(Json.obj("error" -> "NOT_FOUND"))
      case Some(favourite) => Ok(Json.obj("result" -> favourite))
    }
  }

  def finaAll(userId: Long) = Action {
    val allFavourites = FavouriteStudio.findAllByUser(userId)

    Ok(Json.obj("result" -> allFavourites))
  }
}
