import play.api.libs.json.Json

package object models {

  implicit val addressWrites = Json.writes[OutputAdress]
  implicit val contactWrites = Json.writes[OutputContact]
  implicit val locationWrites = Json.writes[Location]
  implicit val listingWrites = Json.writes[OutputListing]

  implicit val addressReades = Json.reads[OutputAdress]
  implicit val contactReades = Json.reads[OutputContact]
  implicit val locationReades = Json.reads[Location]
  implicit val listingReades = Json.reads[OutputListing]
}