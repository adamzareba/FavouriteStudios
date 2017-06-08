package repositories

import javax.inject.Inject

import anorm._
import models.FavouriteStudio
import play.api.db._

trait FavouritesStudioRepository {

  def create(favourite: FavouriteStudio)

  def delete(favourite: FavouriteStudio)

  def exists(favourite: FavouriteStudio): Boolean

  def list(userId: Long): List[FavouriteStudio]
}

class FavouritesStudioRepositoryImpl @Inject()(database: Database) extends FavouritesStudioRepository {

  override def create(favourite: FavouriteStudio) =
    database.withConnection { implicit connection =>
      SQL(
        """
          INSERT IGNORE INTO favouriteStudio(USER_ID, STUDIO_ID) VALUES ({userId}, {studioId})
        """).on(
        "userId" -> favourite.userId,
        "studioId" -> favourite.studioId
      ).executeInsert()
    }

  override def delete(favourite: FavouriteStudio) = {
    database.withConnection { implicit connection =>
      SQL(
        """
          DELETE FROM favouriteStudio WHERE USER_ID = {userId} AND STUDIO_ID = {studioId} LIMIT 1
        """).on(
        "userId" -> favourite.userId,
        "studioId" -> favourite.studioId
      ).executeUpdate()
    }
  }

//  override def exists(favourite: FavouriteStudio): Boolean = {
//    database.withConnection { implicit connection =>
//      val result = SQL(
//        """
//          SELECT COUNT(*) as numMatches FROM favouriteStudio WHERE USER_ID = {userId} AND STUDIO_ID = {studioId}
//        """).on(
//        "userId" -> favourite.userId,
//        "studioId" -> favourite.studioId
//      ).apply().head
//
//      result[Int]("numMatches") != 0
//    }
//  }
//
//  override def list(userId: Long): List[FavouriteStudio] = {
//    database.withConnection { implicit connection =>
//      val results = SQL(
//        """
//          SELECT USER_ID, STUDIO_ID FROM favouriteStudio WHERE USER_ID = {userId}
//        """).on(
//        "userId" -> userId
//      ).apply()
//
//      results.map { row =>
//        FavouriteStudio(row[Long]("USER_ID"), row[Long]("STUDIO_ID"))
//      }.force.toList
//    }
//  }
  override def exists(favourite: FavouriteStudio): Boolean = ???

  override def list(userId: Long): List[FavouriteStudio] = ???
}
