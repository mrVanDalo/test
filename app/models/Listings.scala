package models

import java.util.UUID

case class Listing(contact: Contact, address: Address, location: Location)
case class Contact(phoneNumber: String)
case class Address(street: String, postalCode: String, countryCode: String, city: String, stateCode: String)

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

