# Play Api Exercises

Create a REST Api as exercise.

## How to run

```shell
sbt run
```

## How to run tests

```shell
sbt test
```

## Notes

Here are some notes/information about this project.

### TDD

I used Test Driven Development to solve this issue.
There was always a test that needs to be fixed to add

### Database Model and REST model are different

I don't like to mix the database models and the API models.
It results sooner or later into problems which are hard to solve.

### Play + Guice as Framework and DI

I used the playframework before and this is why I chose it.
It comes nowadays with Guice for DI which I prefer over the 
cake-pattern for Servers.


### default.nix

This is my [nix-shell](https://nixos.org/) definition
which installs all programs I need.

# API definition

I wanted wanted to deliver a `swagger.yaml` under `/api/v1/swagger.yaml`
but I reached my time-limit, so I just sum it up here.

## GET /api/v1/listing/:id

returns the listing for `id` if existing.

### Example

```json
{
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
```

## POST /api/v1/listing

save a listing and returns an id to get it back again

### Example

```shell
curl --include \                                                                                                                                                                          ✔  13:11:08 
  --request POST \
  --header "Content-type: application/json" \
  --data '{"contact":{
            "phoneNumber":"+41123456"
           },
           "location":{
            "lng":1,
            "lat":2
           },
           "address":{
            "street":"waterloo",
            "postalCode":"12321",
            "countryCode":"GB",
            "city":"London",
            "stateCode":"LO"
           }
          }' \
  http://localhost:9000/api/v1/listing/
```

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
