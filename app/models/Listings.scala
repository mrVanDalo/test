package models

import java.util.UUID

class Listings {

}

case class Listing(
    id: UUID,
    contact: Contact,
    address: Address,
    location: Location)
case class Contact(
    phoneNumber: String,
    formattedPhone: String)
case class Address(
    street: String,
    postalCode: String,
    countryCode: String,
    city: String,
    stateCode: String,
    country: String)
case class Location(
    lat: Double,
    lng: Double)

