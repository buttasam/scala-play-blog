package controllers

import javax.inject._
import models.form.LoginForm
import models.service.LoginService
import play.api.mvc._

@Singleton
class LoginController @Inject()(cc: ControllerComponents, loginService: LoginService) extends AbstractController(cc) with play.api.i18n.I18nSupport {


  def login() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login(LoginForm.loginForm))
  }

  def loginFormPost() = Action { implicit request =>
    LoginForm.loginForm.bindFromRequest.fold(
      _ => {
        BadRequest
      },
      data => {
        if (loginService.verifyLogin(data.name, data.password)) {
          Redirect(routes.HomeController.index())
            .withSession(models.Global.SESSION_USERNAME_KEY -> data.name)
        } else {
          Redirect(routes.LoginController.login())
        }
      }
    )
  }


  def logout = Action { implicit request: Request[AnyContent] =>
    Redirect(routes.LoginController.login())
      .withNewSession
  }

}
