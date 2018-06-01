package controllers

import javax.inject._
import models.form.LoginForm
import play.api.mvc._

@Singleton
class LoginController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {


  def login() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login(LoginForm.loginForm))
  }

  def loginFormPost() = Action { implicit request =>
    val formData: LoginForm = LoginForm.loginForm.bindFromRequest.get // Careful: BasicForm.form.bindFromRequest returns an Option
    Ok(formData.toString) // TODO
  }


}
