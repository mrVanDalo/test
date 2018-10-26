package models

import java.util.UUID

class Listings {

}

case class OutputListing(
    id: UUID,
    contact: OutputContact,
    address: OutputAdress,
    location: Location)
case class OutputContact(
    phoneNumber: String,
    formattedPhone: String)
case class OutputAdress(
    street: String,
    postalCode: String,
    countryCode: String,
    city: String,
    stateCode: String,
    country: String)

case class Location(
    lat: Double,
    lng: Double)

