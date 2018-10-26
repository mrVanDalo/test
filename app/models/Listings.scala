package models

import java.util.UUID

case class Listing(
    contact: Contact,
    address: Address,
    location: Location
)
case class Contact(phoneNumber: String)
case class Address(
    street: String,
    postalCode: String,
    countryCode: String,
    city: String,
    stateCode: String
)
case class Location(
    latitude: Double,
    longitude: Double
)

case class InputListing(
    contact: InputContact,
    address: InputAddress,
    location: InputLocation
)
case class InputContact(phoneNumber: String)
case class InputAddress(
    street: String,
    postalCode: String,
    countryCode: String,
    city: String,
    stateCode: String
)
case class InputLocation(
    lat: Double,
    lng: Double
)

case class OutputListing(
    id: UUID,
    contact: OutputContact,
    address: OutputAddress,
    location: OutputLocation
)
case class OutputContact(
    phoneNumber: String,
    formattedPhone: String
)
case class OutputAddress(
    street: String,
    postalCode: String,
    countryCode: String,
    city: String,
    stateCode: String,
    country: String
)

case class OutputLocation(
    lat: Double,
    lng: Double
)

