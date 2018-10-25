package services.database

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

import scala.concurrent.{ ExecutionContext, Future }

class InMemoryDatabaseService {

  /**
   * holds the state of the database
   */
  var map: ConcurrentHashMap[UUID, Listing] = new ConcurrentHashMap[UUID, Listing]()

  def find(id: UUID)(implicit ex: ExecutionContext): Future[Option[Listing]] = Future(
    Option(map.getOrDefault(id, null))
  )

  def save(listing: Listing)(implicit ex: ExecutionContext): Future[UUID] = Future {
    val id = UUID.randomUUID()
    map.put(id, listing)
    id
  }

  def ready() = true

}

case class Listing(contact: Contact, address: Address, location: Location)
case class Contact(phoneNumber: String)
case class Address(street: String, postalCode: String, countryCode: String, city: String, stateCode: String)
case class Location(latitude: Double, longitude: Double)
