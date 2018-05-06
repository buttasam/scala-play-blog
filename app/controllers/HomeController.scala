package controllers

import javax.inject._
import models.repository.UserRepository
import play.api.mvc._


@Singleton
class HomeController @Inject()(cc: ControllerComponents, userRepo: UserRepository) extends AbstractController(cc) {

  def index() = Action {
    // FIXME - just example
    val user = userRepo.findById(1)
    println(user.get.email)
    Ok(views.html.index())
  }
}
