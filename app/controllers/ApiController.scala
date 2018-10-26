package controllers

import java.util.UUID

import javax.inject._
import models._
import play.api.libs.json._
import play.api.mvc._
import services.DatabaseModelMapper
import services.database.DatabaseService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{ Failure, Success, Try }

@Singleton
class ApiController @Inject() (
    cc: ControllerComponents,
    databaseService: DatabaseService,
    mapper: DatabaseModelMapper
) extends AbstractController(cc) {

  def get(id: String) = Action.async { implicit request: Request[AnyContent] =>
    Try(UUID.fromString(id)) match {
      case Failure(_) => Future.successful(BadRequest(
        s"""
           |{"error":"${id} is not a valid UUID"}
           """.stripMargin
      ))
      case Success(uuid) =>
        databaseService.find(uuid)
          .map {
            case Some(data) =>
              Ok(Json.toJson(mapper.mapListing(uuid, data)))
            case None => NotFound
          }
    }
  }

  def post() = Action.async { request =>
    val inputListing = request.body.asJson.flatMap(input => input.validate[InputListing].asOpt)
    inputListing match {
      case Some(listing) =>
        databaseService.save(
          mapper.mapInputToDatabase(listing)
        ).map(id => Ok(s"""{"id":"${id}"}"""))
      case None => Future.successful(BadRequest(
        """
          |{"error":"body is not a valid listing"}
        """.stripMargin
      ))
    }
  }

}

