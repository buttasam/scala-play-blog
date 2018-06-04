package controllers

import javax.inject._
import models.form.ContactForm
import models.repository.ArticleRepository
import play.api.mvc._


@Singleton
class HomeController @Inject()(cc: ControllerComponents, articleRepository: ArticleRepository) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(articleRepository.findAll(), ContactForm.contactForm))
  }


  def contactFormPost() = Action { implicit request =>
    ContactForm.contactForm.bindFromRequest.fold(
      error => {
        println(error)
        BadRequest
      },
      data => {
        Ok(data.toString)
      }
    )
  }


  def article(id: Int) = Action {
    val article = articleRepository.findById(id).get

    Ok(views.html.article(article))
  }
}
