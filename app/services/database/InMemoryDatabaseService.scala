package services.database

import models._
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

