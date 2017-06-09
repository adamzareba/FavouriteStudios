package config

import com.google.inject.AbstractModule
import repositories.{CompanyRepository, CompanyRepositoryImpl, FavouritesStudioRepository, FavouritesStudioRepositoryImpl}
import services.{CompanyService, CompanyServiceImpl, FavouritesStudioService, FavouritesStudioServiceImpl}
import play.api.{Configuration, Environment}

class GuiceModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure() = {
    bind(classOf[FavouritesStudioService]).to(classOf[FavouritesStudioServiceImpl])
    bind(classOf[FavouritesStudioRepository]).to(classOf[FavouritesStudioRepositoryImpl])
    bind(classOf[CompanyRepository]).to(classOf[CompanyRepositoryImpl])
    bind(classOf[CompanyService]).to(classOf[CompanyServiceImpl])
  }
}
