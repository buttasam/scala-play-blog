package controllers

import javax.inject._
import models.form.ContactForm
import models.repository.{ArticleRepository, ContactRepository}
import play.api.mvc._


@Singleton
class HomeController @Inject()(articleRepository: ArticleRepository, contactRepository: ContactRepository, cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(articleRepository.findAll(), ContactForm.contactForm))
  }


  def contactFormPost() = Action { implicit request =>
    ContactForm.contactForm.bindFromRequest.fold(
      _ => {
        BadRequest
      },
      data => {
        contactRepository.insert(data)
        Redirect(routes.HomeController.index())
      }
    )
  }


  def article(id: Int) = Action {
    val article = articleRepository.findById(id).get // FIXME

    Ok(views.html.article(article))
  }
}
