package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
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
            val controller = new ApiController(stubControllerComponents())
            val response = controller.get().apply(FakeRequest(GET, "/api/v1/listing/NOTANUUID"))

            status(response) mustBe BAD_REQUEST
            contentAsString(response) must include("error")
      contentAsString(response) must include("NOTANUUID")
      contentAsString(response) must include("not a valid UUID")

    }

    "returns 404 for unknown UUID" ignore {

    }



    "returns a proper response for known UUID" ignore {
      //      val controller = new HomeController(stubControllerComponents())
      //      val home = controller.index().apply(FakeRequest(GET, "/"))
      //
      //      status(home) mustBe OK
      //      contentType(home) mustBe Some("text/html")
      //      contentAsString(home) must include("Welcome to Play")
    }

//    "render the index page from the application" in {
//      val controller = inject[HomeController]
//      val home = controller.index().apply(FakeRequest(GET, "/"))
//
//      status(home) mustBe OK
//      contentType(home) mustBe Some("text/html")
//      contentAsString(home) must include("Welcome to Play")
//    }

//    "render the index page from the router" in {
//      val request = FakeRequest(GET, "/")
//      val home = route(app, request).get
//
//      status(home) mustBe OK
//      contentType(home) mustBe Some("text/html")
//      contentAsString(home) must include("Welcome to Play")
//    }

  }
}
