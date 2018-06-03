package controllers

import javax.inject._
import models.repository.ArticleRepository
import play.api.mvc._


@Singleton
class HomeController @Inject()(cc: ControllerComponents, articleRepository: ArticleRepository) extends AbstractController(cc) {

  def index() = Action {
    Ok(views.html.index(articleRepository.findAll()))
  }


  def article(id: Int) = Action {
    val article = articleRepository.findById(id).get

    Ok(views.html.article(article))
  }
}
