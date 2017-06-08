package models

import play.api.libs.json.Json

case class Company(id: Option[Long], name: String)

object Company {

  implicit val writer = Json.writes[Company]

  implicit val reader = Json.reads[Company]
}
