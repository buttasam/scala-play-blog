package controllers

import controllers.action.AuthenticatedAction
import javax.inject._
import play.api.mvc._


@Singleton
class AdminController @Inject()(cc: ControllerComponents, authAction: AuthenticatedAction) extends AbstractController(cc) {

  def index() = authAction {
    Ok(views.html.adminIndex())
  }

  def blog() = authAction {
    Ok(views.html.adminBlog())
  }

  def contact() = authAction {
    Ok(views.html.adminContact())
  }

}
