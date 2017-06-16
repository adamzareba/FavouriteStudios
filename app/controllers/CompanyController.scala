package controllers

import com.google.inject.Inject
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import models.Company
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import repositories.CompanyRepository

import scala.concurrent.Future

@Api(value = "Company operations")
class CompanyController @Inject()(companyRepository: CompanyRepository) extends Controller {

  implicit val writer = Json.writes[Company]

  implicit val reader = Json.reads[Company]

  @ApiOperation(value = "Find company", response = classOf[Future[Company]])
  def find(@ApiParam(value = "Company identifier", required = true) id: Long) = Action.async {
    companyRepository.find(id).map(company => Ok(Json.toJson(company)))
  }

  @ApiOperation(value = "Find companies", response = classOf[Future[List[Company]]])
  def findAll = Action.async {
    companyRepository.findAll.map(companies => Ok(Json.toJson(companies)))
  }

  @ApiOperation(value = "Create company", response = classOf[Void])
  @ApiParam(value = "Company identifier", required = true)
  def create = Action { request =>
    val json = request.body.asJson.get
    val company = json.as[Company]
    companyRepository.create(company)
    Ok
  }

  @ApiOperation(value = "Update company", response = classOf[Void])
  def update = Action { request =>
    val json = request.body.asJson.get
    val company = json.as[Company]
    companyRepository.update(company)
    Ok
  }

  @ApiOperation(value = "Remove company", response = classOf[Void])
  def delete(@ApiParam(value = "Company identifier", required = true) id: Long) = Action {
    companyRepository.delete(id)
    Ok
  }
}
