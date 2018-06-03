package controllers

import controllers.action.AuthenticatedAction
import javax.inject._
import models.form.ArticleForm
import play.api.mvc._


@Singleton
class AdminController @Inject()(cc: ControllerComponents, authAction: AuthenticatedAction) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def index() = authAction {
    Ok(views.html.adminIndex())
  }

  def blog() = authAction { implicit request: Request[AnyContent] =>
    Ok(views.html.adminBlog(ArticleForm.articleForm))
  }

  def contact() = authAction {
    Ok(views.html.adminContact())
  }

  def articleFormPost() = Action { implicit request =>
    ArticleForm.articleForm.bindFromRequest.fold(
      error => {
        println(error)
        BadRequest
      },
      data => {
        Ok(data.toString)
      }
    )
  }
}
