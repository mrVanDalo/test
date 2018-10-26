package controllers

import java.util.UUID

import models._
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.{ JsValue, Json }
import play.api.test.Helpers._
import play.api.test._

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

    "returns an error for wrong content" in {

      val injector = new GuiceApplicationBuilder().injector()
      val controller = injector.instanceOf[ApiController]

      val response = controller.post().apply(FakeRequest(POST, "/api/v1/listing/").withJsonBody(
        Json.parse(
          """
          |{"wrong": "data"}
        """.stripMargin
        )
      ))

      status(response) mustBe BAD_REQUEST
      Json.parse(contentAsString(response))
    }

    "returns a proper response for known UUID" in {

      val injector = new GuiceApplicationBuilder().injector()
      val controller = injector.instanceOf[ApiController]

      val postResponse = controller.post().apply(FakeRequest(POST, "/api/v1/listing/").withJsonBody(Json.parse(
        """
          |{"contact":{
          |  "phoneNumber":"+41123456"
          | },
          | "location":{
          |  "lng":1,
          |  "lat":2
          | },
          | "address":{
          |  "street":"waterloo",
          |  "postalCode":"12321",
          |  "countryCode":"GB",
          |  "city":"London",
          |  "stateCode":"LO"
          | }
          |}
        """.stripMargin
      )))

      status(postResponse) mustBe OK

      // provoke exception to test if the response is an UUID
      val result = Json.parse(contentAsString(postResponse))
      val idValue: JsValue = (result \ "id").get
      val id = idValue.validate[String].get
      val uuid = UUID.fromString(id)

      val response = controller.get(id).apply(FakeRequest(GET, s"/api/v1/listing/${id}"))

      status(response) mustBe OK
      val listing = Json.parse(contentAsString(response)).validate[OutputListing].get

      listing.id mustBe uuid
      listing.contact mustBe OutputContact(
        phoneNumber    = "+41123456",
        formattedPhone = "+41-123-456"
      )
      listing.address mustBe OutputAddress(
        street      = "waterloo",
        postalCode  = "12321",
        countryCode = "GB",
        city        = "London",
        stateCode   = "LO",
        country     = "United Kingdom of Great Britain and Northern Ireland"
      )
      listing.location mustBe OutputLocation(lng = 1, lat = 2)

    }

  }
}
