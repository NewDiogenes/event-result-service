Event Result Service
===================

Description
-----------

- This Application is a proof of concept for an API that would return the results of a sporting competition when queried
- The API was modelled after ESPN's own results API (although only containing a small fraction of the overall number of
  fields), in order to reduce the work that would be required to interface the two at a latter date.
- At this point, there is no method to add more entries to the database, the service returns only the data it loads
during start up.

Example Input and Output
------------------------

Example Input

    curl localhost:8080/competition

Expected Output:

    [{
      "uid": "match1",
      "id": "match1",
      "competitionDate": "2021-1-1",
      "name": "Match 1",
      "competitors": [
          {
              "id": 1,
              "type": "team",
              "score": 111,
              "homeAway": "away",
              "team": {
                  "id": "team1",
                  "uid": "team1",
                  "name": "Team 1"
              },
              "winner": false
          },
          {
              "id": 2,
              "type": "team",
              "score": 222,
              "homeAway": "away",
              "team": {
                  "id": "team2",
                  "uid": "team2",
                  "name": "Team 2"
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
