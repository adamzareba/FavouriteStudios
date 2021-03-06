package controllers

import javax.inject.Inject

import io.swagger.annotations._
import models.FavouriteStudio
import play.api.cache.{CacheApi, Cached}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Writes}
import play.api.mvc.{Action, Controller}
import services.FavouritesStudioService

@Api(value = "Favourite Studios operations")
class FavouriteStudioController @Inject()(cache: CacheApi, cached: Cached, favouriteStudioService: FavouritesStudioService) extends Controller {

  implicit val favouriteStudioWrites: Writes[FavouriteStudio] = (
    (JsPath \ "userId").write[Long] and
      (JsPath \ "studioId").write[Long]
    ) (unlift(FavouriteStudio.unapply))

  private def clearCaches(userId: Long, studioId: Long) =
    List(
      "find_" + userId + "_" + studioId,
      "finaAll_" + userId
    ).map { key =>
      cache.remove(key)
    }

  @ApiOperation(value = "Add connection User to Studio", response = classOf[FavouriteStudio])
  def add(@ApiParam(value = "User identifier", required = true) userId: Long,
          @ApiParam(value = "Studio identifier", required = true) studioId: Long) = Action {
    val favourite = favouriteStudioService.addFavourite(userId, studioId)
    clearCaches(userId, studioId)

    //    Ok(Json.toJson(favourite))
    Ok(Json.obj("result" -> favourite))
  }

  @ApiOperation(value = "Remove connection User to Studio", response = classOf[Void])
  def remove(@ApiParam(value = "User identifier", required = true) userId: Long,
             @ApiParam(value = "Studio identifier", required = true) studioId: Long) = Action {
    favouriteStudioService.delete(userId, studioId)
    clearCaches(userId, studioId)

    Ok(Json.obj("result" -> Json.obj()))
  }

  @ApiOperation(value = "Find connection between User and Studio", response = classOf[FavouriteStudio])
  def find(@ApiParam(value = "User identifier", required = true) userId: Long,
           @ApiParam(value = "Studio identifier", required = true) studioId: Long) = cached("find_" + userId + "_" + studioId) {
    Action.async {
      favouriteStudioService.find(userId, studioId).map(favourite => Ok(Json.obj("result" -> favourite)))
    }
  }

  @ApiOperation(value = "Find connection between User and all Studios", response = classOf[List[FavouriteStudio]])
  def finaAll(@ApiParam(value = "User identifier", required = true) userId: Long) = cached("findAll_" + userId) {
    Action.async {
      favouriteStudioService.findAllByUser(userId).map(allFavourites => Ok(Json.obj("result" -> allFavourites)))
    }
  }
}
