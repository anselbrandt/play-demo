package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.ws._

import scala.concurrent.{ExecutionContext}

/** This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject() (
    ws: WSClient,
    val controllerComponents: ControllerComponents
)(implicit ec: ExecutionContext)
    extends BaseController {

  /** Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  def hello() = Action { implicit request: Request[AnyContent] =>
    Ok("hello from play")
  }
  def apiRoute() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("message" -> "hello"))
  }
  def gateway() = Action.async { implicit request: Request[AnyContent] =>
    ws.url("https://api.kanye.rest").get().map { response =>
      Ok(response.json)
    }
  }
}
