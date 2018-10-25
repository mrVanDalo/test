package services

import java.util.UUID

import com.vitorsvieira.iso.ISOCountry.ISOCountry
import models.{ Address, Contact, Listing, Location }
import services.{ database => db }
import com.vitorsvieira.iso._

/**
 * map database objects to REST Api objects
 */
class ListingsMapper {

  def mapContact(contact: db.Contact): Contact = Contact(
    phoneNumber = contact.phoneNumber,
    formattedPhone = {
      val number = contact.phoneNumber
      s"${number.take(3)}-${number.slice(3, 6)}-${number.drop(6)}"
    }
  )

  def mapLocation(location: db.Location): Location = Location(
    lat = location.latitude,
    lng = location.longitude
  )

  def mapAddress(address: db.Address): Address = Address(
    street = address.street,
    stateCode = address.stateCode,
    city = address.city,
    postalCode = address.postalCode,
    country = ISOCountry.from(address.countryCode)
      .map(country => country.englishName).getOrElse("Unknown"),
    countryCode = address.countryCode
  )

  def mapListing(id: UUID, listing: db.Listing): Listing =
    Listing(
      id = id,
      contact = mapContact(listing.contact),
      address = mapAddress(listing.address),
      location = mapLocation(listing.location)
    )

}
