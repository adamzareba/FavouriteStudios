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
}
