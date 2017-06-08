package controllers

import io.swagger.annotations._
import models.FavouriteStudio
import play.api.Play.current
import play.api.cache.Cached
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import play.cache.Cache

@Api(value = "Favourite Studios operations")
class FavouriteStudios extends Controller {

  private def clearCaches(userId: Long, studioId: Long) =
    List(
      "find_" + userId + "_" + studioId,
      "finaAll_" + userId
    ).map { key =>
      Cache.remove(key)
    }

  @ApiOperation(value = "Add connection User to Studio", response = classOf[FavouriteStudio])
  def add(@ApiParam(value = "User identifier", required = true) userId: Long,
          @ApiParam(value = "Studio identifier", required = true) studioId: Long) = Action {
    val favourite = FavouriteStudio.addFavourite(userId, studioId)
    clearCaches(userId, studioId)

    //    Ok(Json.toJson(favourite))
    Ok(Json.obj("result" -> favourite))
  }

  @ApiOperation(value = "Remove connection User to Studio", response = classOf[Void])
  def remove(@ApiParam(value = "User identifier", required = true) userId: Long,
             @ApiParam(value = "Studio identifier", required = true) studioId: Long) = Action {
    FavouriteStudio.delete(userId, studioId)
    clearCaches(userId, studioId)

    Ok(Json.obj("result" -> Json.obj()))
  }

  @ApiOperation(value = "Find connection between User and Studio", response = classOf[FavouriteStudio])
  def find(@ApiParam(value = "User identifier", required = true) userId: Long,
           @ApiParam(value = "Studio identifier", required = true) studioId: Long) = Cached("find_" + userId + "_" + studioId) {
    Action {
      val optionFavourite = FavouriteStudio.find(userId, studioId)

      optionFavourite match {
        case None => NotFound(Json.obj("error" -> "NOT_FOUND"))
        case Some(favourite) => Ok(Json.obj("result" -> favourite))
      }
    }
  }

  @ApiOperation(value = "Find connection between User and all Studios", response = classOf[List[FavouriteStudio]])
  def finaAll(@ApiParam(value = "User identifier", required = true) userId: Long) = Cached("findAll_" + userId) {
    Action {
      val allFavourites = FavouriteStudio.findAllByUser(userId)

      Ok(Json.obj("result" -> allFavourites))
    }
  }
}
