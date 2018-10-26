import play.api.libs.json.Json

package object models {

  implicit val addressWrites = Json.writes[OutputAddress]
  implicit val contactWrites = Json.writes[OutputContact]
  implicit val locationWrites = Json.writes[Location]
  implicit val listingWrites = Json.writes[OutputListing]

  implicit val addressReades = Json.reads[OutputAddress]
  implicit val contactReades = Json.reads[OutputContact]
  implicit val locationReades = Json.reads[Location]
  implicit val listingReades = Json.reads[OutputListing]

  implicit val inputAddressWrites = Json.writes[InputAddress]
  implicit val inputContactWrites = Json.writes[InputContact]
  //  implicit val inputLocationWrites = Json.writes[Location]
  implicit val inputListingWrites = Json.writes[InputListing]

  implicit val inputAddressReades = Json.reads[InputAddress]
  implicit val inputContactReades = Json.reads[InputContact]
  //  implicit val inputLocationReades = Json.reads[Location]
  implicit val inputListingReades = Json.reads[InputListing]
}
