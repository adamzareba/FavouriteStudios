package modules

import com.google.inject.AbstractModule
import play.api.{Configuration, Environment}
import repositories.{CompanyRepository, CompanyRepositoryImpl, FavouritesStudioRepository, FavouritesStudioRepositoryImpl}
import services.{FavouritesStudioService, FavouritesStudioServiceImpl}

class GuiceModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure() = {
    bind(classOf[FavouritesStudioService]).to(classOf[FavouritesStudioServiceImpl])
    bind(classOf[FavouritesStudioRepository]).to(classOf[FavouritesStudioRepositoryImpl])
    bind(classOf[CompanyRepository]).to(classOf[CompanyRepositoryImpl])
  }
}
