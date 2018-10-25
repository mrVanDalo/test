package services

import java.util.UUID

import models.{ Address, Contact, Listing, Location }
import services.{ database => db }

/**
 * map database objects to REST Api objects
 */
class ListingsMapper {

  def mapContact(contact: db.Contact): Contact = Contact(
    phoneNumber = contact.phoneNumber,
    formattedPhone = contact.phoneNumber
  )

  def mapLocation(location: db.Location): Location = Location(
    lat = location.latitude, lng = location.longitude
  )

  def mapAddress(address: db.Address): Address = Address(
    street = address.street,
    stateCode = address.stateCode,
    city = address.city,
    postalCode = address.postalCode,
    country = address.countryCode,
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
