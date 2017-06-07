package models.dao

import anorm._
import models.FavouriteStudio
import play.api.Play.current
import play.api.db._

object FavouritesStudioDAO {

  def create(favourite: FavouriteStudio) =
    DB.withConnection { implicit connection =>
      SQL(
        """
          |INSERT IGNORE INTO favouriteStudio(userId, studioId)
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
          |WHERE userId = {userId} AND studioId = {studioId}
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
          |WHERE userId = {userId} AND studioId = {studioId};
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
          |SELECT userId, studioId
          |FROM favouriteStudio
          |WHERE userId = {userId};
        """.stripMargin).on(
        "userId" -> userId
      ).apply()

      results.map { row =>
        FavouriteStudio(row[Long]("userId"), row[Long]("studioId"))
      }.force.toList
    }
  }
}
