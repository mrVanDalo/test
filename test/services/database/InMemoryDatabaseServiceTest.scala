package services.database

import java.util.UUID
import java.util.concurrent.TimeUnit

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global


class InMemoryDatabaseServiceTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "Database.ready()" should {
    "return true" in {
      val database = new InMemoryDatabaseService()
      database.ready() === true
    }
  }

  "Database.save()" should {
    "return an UUID for successful storing " in {
      val database = new InMemoryDatabaseService()

      val result = database.save( validData )
      // failes if result is failing
      Await.result(result, standardDuration)
    }

    "stores data" in {
      val database = new InMemoryDatabaseService()

      val id : UUID = Await.result(database.save(validData), standardDuration)
      val result : Option[_] = Await.result( database.find(id), standardDuration )
      result.isDefined mustBe(true)
    }
  }

  "Database.find()" should {

    "return the same data just added" in {
      val database = new InMemoryDatabaseService()

      val id : UUID = Await.result(database.save(validData), standardDuration)
      val result : Option[Listing] = Await.result( database.find(id), standardDuration )

      result.isDefined mustBe(true)
      result.get mustEqual(validData)
    }

    "return None for something not exisiting" in {
      val database = new InMemoryDatabaseService()

      val result : Option[Listing] = Await.result( database.find(UUID.randomUUID()), standardDuration )
      result.isDefined mustBe(false)
    }

  }


  val validData = Listing(
    Contact(
      "12123123123"
    ),
    Address(
      "1011 W 5th St",
      "1011",
      "US",
      "Austin",
      "TX",
    ),
    Location(
      40.4255485534668,
      -3.7075681686401367
    )
  )

  val standardDuration = Duration(2, TimeUnit.SECONDS)

}
