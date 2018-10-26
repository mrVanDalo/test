package services

import java.util.UUID

import models.{ OutputAdress, OutputContact, OutputListing, Location }
import services.{ database => db }
import com.vitorsvieira.iso._

/**
 * map database objects to REST Api objects
 */
class DatabaseModelMapper {

  def mapContact(contact: db.Contact): OutputContact = OutputContact(
    phoneNumber = contact.phoneNumber,
    formattedPhone = {
      val number = contact.phoneNumber
      number.length match {
        case n if n > 6 =>
          s"${number.take(3)}-${number.slice(3, 6)}-${number.drop(6)}"
        case n if n > 3 =>
          s"${number.take(3)}-${number.slice(3, 6)}"
        case _ => number
      }
    }
  )

  def mapLocation(location: db.Location): Location = Location(
    lat = location.latitude,
    lng = location.longitude
  )

  def mapAddress(address: db.Address): OutputAdress = OutputAdress(
    street = address.street,
    stateCode = address.stateCode,
    city = address.city,
    postalCode = address.postalCode,
    country = ISOCountry.from(address.countryCode)
      .map(country => country.englishName).getOrElse("Unknown"),
    countryCode = address.countryCode
  )

  def mapListing(id: UUID, listing: db.Listing): OutputListing =
    OutputListing(
      id = id,
      contact = mapContact(listing.contact),
      address = mapAddress(listing.address),
      location = mapLocation(listing.location)
    )

}
