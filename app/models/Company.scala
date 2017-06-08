package models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Writes}

case class Company(id: Long, name: String)

object Company {

  implicit val writes: Writes[Company] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "name").write[String]
    ) (unlift(Company.unapply))
}
