import play.api.libs.json.Json

package object models {

  implicit val outputAddressWrites = Json.writes[OutputAddress]
  implicit val outputContactWrites = Json.writes[OutputContact]
  implicit val outputLocationWrites = Json.writes[OutputLocation]
  implicit val outputListingWrites = Json.writes[OutputListing]

  implicit val outputAddressReades = Json.reads[OutputAddress]
  implicit val outputContactReades = Json.reads[OutputContact]
  implicit val outputLocationReades = Json.reads[OutputLocation]
  implicit val outputListingReades = Json.reads[OutputListing]

  implicit val inputAddressWrites = Json.writes[InputAddress]
  implicit val inputContactWrites = Json.writes[InputContact]
  implicit val inputLocationWrites = Json.writes[InputLocation]
  implicit val inputListingWrites = Json.writes[InputListing]

  implicit val inputAddressReades = Json.reads[InputAddress]
  implicit val inputContactReades = Json.reads[InputContact]
  implicit val inputLocationReades = Json.reads[InputLocation]
  implicit val inputListingReades = Json.reads[InputListing]
}
