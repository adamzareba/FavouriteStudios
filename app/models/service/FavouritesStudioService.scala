package models.service

import javax.inject.Inject

import models.FavouriteStudio
import models.repository.FavouritesStudioRepository

class FavouritesStudioService @Inject()(favouritesStudioRepository: FavouritesStudioRepository) {

  def addFavourite(userId: Long, studioId: Long): FavouriteStudio = {
    val favourite = FavouriteStudio(userId, studioId)
    favouritesStudioRepository.create(favourite)

    favourite
  }

  def delete(userId: Long, studioId: Long) = favouritesStudioRepository.delete(FavouriteStudio(userId, studioId))

  def findAllByUser(userId: Long): List[FavouriteStudio] = favouritesStudioRepository.index(userId)

  def find(userId: Long, studioId: Long): Option[FavouriteStudio] = {
    val favourite = FavouriteStudio(userId, studioId)

    if (favouritesStudioRepository.exists(favourite))
      Some(favourite)
    else
      None
  }

}
