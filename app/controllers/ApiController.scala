package controllers

import java.util.UUID

import javax.inject._
import models._
import play.api.libs.json.Json
import play.api.mvc._
import services.DatabaseModelMapper
import services.database.InMemoryDatabaseService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{ Failure, Success, Try }

@Singleton
class ApiController @Inject() (
    cc: ControllerComponents,
    databaseService: InMemoryDatabaseService,
    mapper: DatabaseModelMapper
) extends AbstractController(cc) {

  def get(id: String) = Action.async { implicit request: Request[AnyContent] =>
    Try(UUID.fromString(id)) match {
      case Failure(_) => Future.successful(BadRequest(s"""{"error":"${id} is not a valid UUID"}"""))
      case Success(uuid) =>
        databaseService.find(uuid)
          .map {
            case Some(data) =>
              Ok(Json.toJson(mapper.mapListing(uuid, data)))
            case None => NotFound
          }
    }
  }

  def post() = Action(Ok("hallo"))

}

