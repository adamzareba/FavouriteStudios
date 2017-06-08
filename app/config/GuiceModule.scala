package config

import com.google.inject.AbstractModule
import repository.{FavouritesStudioRepository, FavouritesStudioRepositoryImpl}
import service.{FavouritesStudioService, FavouritesStudioServiceImpl}
import play.api.{Configuration, Environment}

class GuiceModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure() = {
    bind(classOf[FavouritesStudioService]).to(classOf[FavouritesStudioServiceImpl])
    bind(classOf[FavouritesStudioRepository]).to(classOf[FavouritesStudioRepositoryImpl])

  }
}
