Event Result Service
===================

Description
-----------

- This Application is a proof of concept for an API that would return the results of a sporting competition when queried
- The API was modelled after ESPN's own results API (although only containing a small fraction of the overall number of
  fields), in order to reduce the work that would be required to interface the two at a latter date.

Example Input and Output
------------------------

Example Input

    curl localhost:8080/competition/results

Expected Output:

    [{
      "uid": 1",
      "competitionDate": "2021-02-08T20:00:00Z",
      "name": "Match 1",
      "competitors": [
          {
              "id": 1,
              "score": 111,
              "homeAway": "away",
              "team": {
                  "id": 1,
                  "shortName": "Team 1"
                  "longName": "Team 1"
                  "abbreviatedName": "T1"
              },
              "winner": false
          },
          {
              "id": 2,
              "score": 222,
              "homeAway": "away",
              "team": {
                  "id": 1,
                  "shortName": "Team 2"
                  "longName": "Team 2"
                  "abbreviatedName": "T2"
              },
              "winner": false
          }
      ]
    }]

Usage
------------

Run tests:

    ./gradlew test

Run application:

    ./gradlew run

Notes
-------

There are a few aspects of this service that would ideally be changed/improved before deploying to production
- The service currently uses the same class for its data entry and response objects,ordinarily they should be
  separated to minimise dependencies between then controller and repository layers.
- This service currently contains no exception handling. At this point the service is simple enough not to require
  exception handling since it has only a single use case, but you would expect exception handling to be added for
  more complex use cases.
- This service currently relies on Spring to handle http response codes in failure scenarios. Normally, this would
  be handled by the application by mapping various exception conditions to different http error codes.
- This service has a second endpoint, which was intended to be used to initialise web scraping of the specified ESPN
  sports page, which is not currently functional. ESPN appears to have code in place to prevent scraping, so for the
  time being the service only has the data it scrapes from a test page (which contains html directly taken from
  espn.com.au/football) on start up.