package controllers

import com.google.inject.Inject
import io.swagger.annotations.{Api, ApiOperation, ApiParam}
import models.Company
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.CompanyService

@Api(value = "Company operations")
class CompanyController @Inject()(companyService: CompanyService) extends Controller {

  @ApiOperation(value = "Find company", response = classOf[Company])
  def find(@ApiParam(value = "Company identifier", required = true) id: Long) = Action {
    val company = companyService.find(id)
    Ok(Json.toJson(company))
  }

  @ApiOperation(value = "Find companies", response = classOf[List[Company]])
  def findAll = Action {
    val companies = companyService.findAll
    Ok(Json.toJson(companies))
  }

  @ApiOperation(value = "Create company", response = classOf[Void])
  @ApiParam(value = "Company identifier", required = true)
  def create = Action { request =>
    val json = request.body.asJson.get
    val company = json.as[Company]
    companyService.create(company)
    Ok
  }

  @ApiOperation(value = "Update company", response = classOf[Void])
  def update = Action { request =>
    val json = request.body.asJson.get
    val company = json.as[Company]
    companyService.update(company)
    Ok
  }

  @ApiOperation(value = "Remove company", response = classOf[Void])
  def delete(@ApiParam(value = "Company identifier", required = true) id: Long) = Action {
    companyService.delete(id)
    Ok
  }
}
