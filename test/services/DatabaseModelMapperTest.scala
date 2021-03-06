package services

import java.util.UUID

import models._
import services.database._
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting

class DatabaseModelMapperTest extends PlaySpec with GuiceOneAppPerTest with Injecting {


  "mapListing" should {
    "not delete old parameters" in {
      val mapper = new DatabaseModelMapper()

      val id = UUID.randomUUID()
      val result: OutputListing = mapper.mapListing(id, validData)

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

  "mapContact" should {
    "properly format phone numbers" in {
      val mapper = new DatabaseModelMapper()

      val dbContact = Contact("+412345232")

      val result : OutputContact = mapper.mapContact(dbContact)

      result.phoneNumber mustEqual dbContact.phoneNumber
      result.formattedPhone mustEqual "+41-234-5232"
    }
    "properly format short phone numbers" in {
      val mapper = new DatabaseModelMapper()

      val dbContact = Contact("+41234")

      val result : OutputContact = mapper.mapContact(dbContact)

      result.phoneNumber mustEqual dbContact.phoneNumber
      result.formattedPhone mustEqual "+41-234"
    }
  }

  "mapAddress" should {
    "find correct Country " in {
      val mapper = new DatabaseModelMapper()

      val dbAddress =
      Address(
        "1011 W 5th St",
        "1011",
        "US",
        "Austin",
        "TX",
      )

      val result : OutputAddress = mapper.mapAddress(dbAddress)

      result.country mustEqual "United States of America"
    }
  }

  val validData = Listing(
    contact = Contact(
      phoneNumber = "12123123123"
    ),
    address = Address(
      street = "1011 W 5th St",
      postalCode = "1011",
      countryCode = "US",
      city = "Austin",
      stateCode = "TX",
    ),
    location = Location(
      latitude = 40.4255485534668,
      longitude = -3.7075681686401367
    )
  )

}
