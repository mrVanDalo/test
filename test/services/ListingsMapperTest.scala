package services

import java.util.UUID

import models.{Address, Contact, Listing}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting
import services.{database => db}

class ListingsMapperTest extends PlaySpec with GuiceOneAppPerTest with Injecting {


  "mapListing" should {
    "not delete old parameters" in {
      val mapper = new ListingsMapper()

      val id = UUID.randomUUID()
      val result: Listing = mapper.mapListing(id, validData)

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
      val mapper = new ListingsMapper()

      val dbContact = db.Contact("+412345232")

      val result : Contact = mapper.mapContact(dbContact)

      result.phoneNumber mustEqual dbContact.phoneNumber
      result.formattedPhone mustEqual "+41-234-5232"
    }
    "properly format short phone numbers" in {
      val mapper = new ListingsMapper()

      val dbContact = db.Contact("+41234")

      val result : Contact = mapper.mapContact(dbContact)

      result.phoneNumber mustEqual dbContact.phoneNumber
      result.formattedPhone mustEqual "+41-234"
    }
  }

  "mapAddress" should {
    "find correct Country " in {
      val mapper = new ListingsMapper()

      val dbAddress =
      db.Address(
        "1011 W 5th St",
        "1011",
        "US",
        "Austin",
        "TX",
      )

      val result : Address = mapper.mapAddress(dbAddress)

      result.country mustEqual "United States of America"
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
