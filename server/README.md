# server
The Spark Java REST server for Company Management. It runs on port 4567 by default. To check that it's up, open http://localhost:4567/helloworld.

Run all of the following commands from `server/`.

## Start server

Run `mvn exec:exec`, or run the `Main` class from your IDE.

## Package server into standalone JAR

Run `mvn package`.

## Run tests with JaCoCo's coverage

Run `mvn test`.

The coverage report is written to `server/target/site/jacoco`.

## Run mutation testing with PIT

Run `mvn test-compile org.pitest:pitest-maven:mutationCoverage`.

The report is written to `server/target/pit-reports`.
