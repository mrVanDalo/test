package services.database

import java.util.UUID

import scala.concurrent.Future

class InMemoryDatabaseService {

  var map: Map[UUID, Listing] = Map[UUID, Listing]()

  def find(id: UUID): Future[Option[Listing]] = Future.successful(
    Some(Listing(Contact(""), Address("", "", "", "", ""), Location(0, 0)))
  )

  def save(listing: Listing): Future[UUID] = Future.successful(UUID.randomUUID())

  def ready() = true

}

case class Listing(contact: Contact, address: Address, location: Location)
case class Contact(phoneNumber: String)
case class Address(street: String, postalCode: String, countryCode: String, city: String, stateCode: String)
case class Location(latitude: Double, longitude: Double)
