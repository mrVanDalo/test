package controllers

import java.util.UUID

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.inject.guice.{ GuiceApplicationBuilder, GuiceInjectorBuilder }
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class ApiControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "/api/v1/listing" should {

    "returns 503 for wrong UUID" in {
      val injector = new GuiceApplicationBuilder().injector()
      val controller = injector.instanceOf[ApiController]
      val response = controller.get("NOTANUUID").apply(FakeRequest(GET, "/api/v1/listing/"))

      status(response) mustBe BAD_REQUEST
      contentAsString(response) must include("error")
      contentAsString(response) must include("NOTANUUID")
      contentAsString(response) must include("not a valid UUID")

    }

    "returns 404 for unknown UUID" in {
      val injector = new GuiceApplicationBuilder().injector()
      val controller = injector.instanceOf[ApiController]

      val uuid = UUID.randomUUID()
      val response = controller.get(uuid.toString).apply(FakeRequest(GET, "/api/v1/listing/"))

      status(response) mustBe NOT_FOUND
    }

    "returns a proper response for known UUID" in {
      val injector = new GuiceApplicationBuilder().injector()
      val controller = injector.instanceOf[ApiController]

      val response = controller.post().apply(FakeRequest(POST, "/api/v1/listing/"))
      status(response) mustBe OK
      Json.parse(contentAsString(response))
    }

  }
}
