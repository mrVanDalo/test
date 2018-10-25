package services

import java.util.UUID

import models.Listing
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting
import services.{database => db}

class ListingsMapperTest extends PlaySpec with GuiceOneAppPerTest with Injecting {


  "map" should {
    "not delete old parameters" in {
      val mapper = new ListingsMapper()

      val id = UUID.randomUUID()
      val result : Listing = mapper.mapListing(id, validData)
      result.id mustEqual id

      result.contact.phoneNumber mustEqual validData.contact.phoneNumber

      result.address.street mustEqual validData.address.street
      result.address.city mustEqual validData.address.city
      result.address.postalCode mustEqual validData.address.postalCode
      result.address.countryCode mustEqual validData.address.countryCode
      result.address.stateCode mustEqual validData.address.stateCode

      result.location.lat mustEqual validData.location.latitude
      result.location.lng mustEqual validData.location.longitude
    }
  }

  val validData = db.Listing(
    db.Contact(
      "12123123123"
    ),
    db.Address(
      "1011 W 5th St",
      "1011",
      "US",
      "Austin",
      "TX",
    ),
    db.Location(
      40.4255485534668,
      -3.7075681686401367
    )
  )

}
