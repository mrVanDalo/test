# Play Api Exercises

An exercises


* TDD
* database model and render model are different
* Interface driven (implementation needs to be correct, and the interface should be easy)
* Why Guice and not cake pattern

# Exercise definition

## Task Instructions

Your task is to build a JSON-based RESTful API for a Vacation Rentals properties.
This is an example of what we expect the JSON vacation rental property document to look
like:

```json
{
  "listing": {
    "id": "5e22a83a-6f4f-11e6-8b77-86f30ca893d3",
    "contact": {
      "phone": "15126841100",
      "formattedPhone": "+1 512-684-1100"
    },
    "address": {
      "address": "1011 W 5th St",
      "postalCode": "1011",
      "countryCode": "US",
      "city": "Austin",
      "state": "TX",
      "country": "United States"
    },
    "location": {
      "lat": 40.4255485534668,
      "lng": -3.7075681686401367
    }
  }
}
```


### Part 1.

The first part of this task is to allow the client to access a listing. It
is up to you to determine the resources and domain objects, but we would
expect that a call such as GET /listings/5e22a83a-6f4f-11e6-8b77-86f30ca893d3 would return a response relating
to the listing with the id of 5e22a83a-6f4f-11e6-8b77-86f30ca893d3.

### Part 2.

The next part of this task is to provide a mechanism to add new listings to
the dataset. This should be provided as an HTTP POST with a parameter body
containing all required data for the new listing. The response should
contain appropriate headers and content for this method. The server should
assign an id to the new listing.

## Additional Notes

Please use either Scala 2.12 or Java 10 (or above). You are free to have it deployed to an application server,
or a standalone JVM.

You are not limited to any particular framework, or even required to use a
framework at all, but you should NOT use a database. We do not expect any data
to persist beyond the lifespan of the server.

Pay particular attention to concurrency issues. Even though the task itself could be achieved
without, imagine you are building for maximum reuse, extensibility, scalability and performance.
Good maintainability and code cleanliness will also gain you extra points.

Ideally, we'd like you to use SBT or Maven to build and structure the app,
with all dependencies accessible through publicly available repositories.
Otherwise, please include all dependencies and a way of building the app.

Once done please send all additional deployment instructions and descriptions of additional features.
Please do not upload the solution to a public indexed repository (e.g. public GitHub). Options are:
1) Zip the source code only and email it as an attachment (ensure it’s less than 250KB).
2) Private Bitbucket repository
3) Private Google Drive
