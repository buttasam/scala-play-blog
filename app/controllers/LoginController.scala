package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class LoginController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def login() = Action {
    Ok(views.html.login())
  }

}
