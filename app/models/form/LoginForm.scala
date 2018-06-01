package models.form

import play.api.data._
import play.api.data.Forms._

case class  LoginForm(name: String, password: String)

object LoginForm {
  val loginForm : Form[LoginForm] = Form(
    mapping(
      "name" -> text,
      "password" -> text
    )(LoginForm.apply)(LoginForm.unapply)
  )

}