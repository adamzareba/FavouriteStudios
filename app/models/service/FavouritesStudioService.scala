package models.service

import anorm._
import models.FavouriteStudio
import play.api.Play.current
import play.api.db._

object FavouritesStudioService {

  def create(favourite: FavouriteStudio) =
    DB.withConnection { implicit connection =>
      SQL(
        """
          |INSERT IGNORE INTO favouriteStudio(USER_ID, STUDIO_ID)
          |VALUES
          |   ({userId}, {studioId});
        """.stripMargin).on(
        "userId" -> favourite.userId,
        "studioId" -> favourite.studioId
      ).executeInsert()
    }

  def delete(favourite: FavouriteStudio) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          |DELETE FROM favouriteStudio
          |WHERE USER_ID = {userId} AND STUDIO_ID = {studioId}
          |LIMIT 1;
        """.stripMargin).on(
        "userId" -> favourite.userId,
        "studioId" -> favourite.studioId
      ).executeUpdate()
    }
  }

  def exists(favourite: FavouriteStudio): Boolean = {
    DB.withConnection { implicit connection =>
      val result = SQL(
        """
          |SELECT COUNT(*) as numMatches
          |FROM favouriteStudio
          |WHERE USER_ID = {userId} AND STUDIO_ID = {studioId};
        """.stripMargin).on(
        "userId" -> favourite.userId,
        "studioId" -> favourite.studioId
      ).apply().head

      result[Int]("numMatches") != 0
    }
  }

  def index(userId: Long): List[FavouriteStudio] = {
    DB.withConnection { implicit connection =>
      val results = SQL(
        """
          |SELECT USER_ID, STUDIO_ID
          |FROM favouriteStudio
          |WHERE USER_ID = {userId};
        """.stripMargin).on(
        "userId" -> userId
      ).apply()

      results.map { row =>
        FavouriteStudio(row[Long]("USER_ID"), row[Long]("STUDIO_ID"))
      }.force.toList
    }
  }
}
