package services.database

import java.util.UUID

import com.google.inject.ImplementedBy

import scala.concurrent.{ ExecutionContext, Future }

@ImplementedBy(classOf[InMemoryDatabaseService])
trait DatabaseService {

  def find(id: UUID)(implicit ex: ExecutionContext): Future[Option[Listing]]

  def save(listing: Listing)(implicit ex: ExecutionContext): Future[UUID]

  def ready()

}

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
