package services.database

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class InMemoryDatabaseServiceTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "Database.ready()" should {

    "return true" in {
      val database = new InMemoryDatabaseService()
      database.ready() === true
    }

  }

}
