package controllers

import controllers.action.AuthenticatedAction
import javax.inject._
import models.form.ArticleForm
import models.repository.ArticleRepository
import play.api.mvc._


@Singleton
class AdminController @Inject()(articleRepository: ArticleRepository, cc: ControllerComponents, authAction: AuthenticatedAction) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def index() = authAction {
    Ok(views.html.adminIndex())
  }

  def blog(articleId : Option[Int]) = authAction { implicit request: Request[AnyContent] =>

    var articleForm = ArticleForm.articleForm

    articleId match {
      case None => {
        articleForm = ArticleForm.articleForm
      }
      case Some(data) => {
        val article = articleRepository.findById(data).get
        articleForm = ArticleForm.articleForm.fill(ArticleForm(article.title, article.perex, article.content))
      }
    }

    Ok(views.html.adminBlog(articleForm, articleRepository.findAll()))
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
        articleRepository.insert(data)
        Redirect(routes.AdminController.blog(None))
      }
    )
  }

  def deleteArticle(id: Int) = authAction {
    articleRepository.deleteArticle(id)
    Redirect(routes.AdminController.blog(None))
  }

}
