package controllers.action

import controllers.routes
import javax.inject.Inject
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Resources:
  * https://www.playframework.com/documentation/2.6.x/ScalaActionsComposition#Authentication
  * https://www.playframework.com/documentation/2.6.x/api/scala/index.html#play.api.mvc.Results@values
  */
class AuthenticatedAction @Inject()(parser: BodyParsers.Default)(implicit ec: ExecutionContext)
  extends ActionBuilderImpl(parser) {

  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {
    val maybeUsername = request.session.get(models.Global.SESSION_USERNAME_KEY)
    maybeUsername match {
      case None =>
        Future.successful(Redirect(routes.LoginController.login()))
      case Some(_) =>
        block(request)
    }
  }
}
