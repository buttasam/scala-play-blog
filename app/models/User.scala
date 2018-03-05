package models

import play.api.libs.json._

case class User(id: Int, email: String)

object User {
    implicit val personFormat: OFormat[User] = Json.format[User]
}