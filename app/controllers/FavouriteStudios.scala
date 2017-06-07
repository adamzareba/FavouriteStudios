package controllers

import models.FavouriteStudio
import play.api.Play.current
import play.api.cache.Cached
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import play.cache.Cache

object FavouriteStudios extends Controller {

  private def clearCaches(userId: Long, studioId: Long) =
    List(
      "find_" + userId + "_" + studioId,
      "finaAll_" + userId
     ).map { key =>
      Cache.remove(key)
    }

  def add(userId: Long, studioId: Long) = Action {
    val favourite = FavouriteStudio.addFavourite(userId, studioId)
    clearCaches(userId, studioId)

    Ok(Json.obj("result" -> favourite))
  }

  def remove(userId: Long, studioId: Long) = Action {
    FavouriteStudio.delete(userId, studioId)
    clearCaches(userId, studioId)

    Ok(Json.obj("result" -> Json.obj()))
  }

  def find(userId: Long, studioId: Long) = Cached("find_" + userId + "_" + studioId) {
    Action {
      val optionFavourite = FavouriteStudio.find(userId, studioId)

      optionFavourite match {
        case None => NotFound(Json.obj("error" -> "NOT_FOUND"))
        case Some(favourite) => Ok(Json.obj("result" -> favourite))
      }
    }
  }

  def finaAll(userId: Long) = Cached("findAll_" + userId) {
    Action {
      val allFavourites = FavouriteStudio.findAllByUser(userId)

      Ok(Json.obj("result" -> allFavourites))
    }
  }
}
