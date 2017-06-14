package services

import javax.inject.Inject

import models.FavouriteStudio
import repositories.FavouritesStudioRepository

trait FavouritesStudioService {

  def addFavourite(userId: Long, studioId: Long): FavouriteStudio

  def delete(userId: Long, studioId: Long)

  def findAllByUser(userId: Long): List[FavouriteStudio]

  def find(userId: Long, studioId: Long): Option[FavouriteStudio]
}

class FavouritesStudioServiceImpl @Inject()(favouritesStudioRepository: FavouritesStudioRepository) extends FavouritesStudioService {

  override def addFavourite(userId: Long, studioId: Long): FavouriteStudio = {
    val favourite = FavouriteStudio(userId, studioId)
    favouritesStudioRepository.create(favourite)

    favourite
  }

//  override def delete(userId: Long, studioId: Long) = favouritesStudioRepository.delete(FavouriteStudio(userId, studioId))
  override def delete(userId: Long, studioId: Long) = favouritesStudioRepository.delete(userId)

  override def findAllByUser(userId: Long): List[FavouriteStudio] = favouritesStudioRepository.list(userId)

  override def find(userId: Long, studioId: Long): Option[FavouriteStudio] = {
    val favourite = FavouriteStudio(userId, studioId)

    if (favouritesStudioRepository.exists(favourite))
      Some(favourite)
    else
      None
  }

}
