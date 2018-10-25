import play.api.libs.json.Json

package object models {

  implicit val addressWrites = Json.writes[Address]
  implicit val contactWrites = Json.writes[Contact]
  implicit val locationWrites = Json.writes[Location]
  implicit val listingWrites = Json.writes[Listing]

  implicit val addressReades = Json.reads[Address]
  implicit val contactReades = Json.reads[Contact]
  implicit val locationReades = Json.reads[Location]
  implicit val listingReades = Json.reads[Listing]
}