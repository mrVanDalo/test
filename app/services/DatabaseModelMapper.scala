package services

import java.util.UUID
import models._
import com.vitorsvieira.iso._

/**
 * map database objects to REST Api objects
 */
class DatabaseModelMapper {

  def mapContact(contact: Contact): OutputContact = OutputContact(
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

  def mapAddress(address: Address): OutputAdress = OutputAdress(
    street = address.street,
    stateCode = address.stateCode,
    city = address.city,
    postalCode = address.postalCode,
    country = ISOCountry.from(address.countryCode)
      .map(country => country.englishName).getOrElse("Unknown"),
    countryCode = address.countryCode
  )

  def mapListing(id: UUID, listing: Listing): OutputListing =
    OutputListing(
      id = id,
      contact = mapContact(listing.contact),
      address = mapAddress(listing.address),
      location = listing.location
    )

}
