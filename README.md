# How to run

## With Docker (preferred way if you don't have Java installed)
* Start the db first: `docker-compose up -d`

* Build the image: `docker build --no-cache -t billy-app:1 -f ./Dockerfile .`

* Run the container and enter it: `docker run -it --rm billy-app:1 /bin/sh`

* Inside the container, run the tests: `./mvnw test` or use the commands described in the next section to run the API.

> I considered coming up with a single docker-compose file that brings everything to life, but it meant creating a service 
> to let the containers communicate, therefore a custom spring profile, and then passing in the correct parameters when 
> launching the app. I thought that writing these 4 commands instead was providing a good enough experience.

## With java installed:
You'll need Java 17 to run this project.

You can run the tests with the `./mvnw test` command.

To run the API, you can use these commands: 
```shell
./mvnw package
java -jar billy-app-0.0.1-SNAPSHOT.jar
```

In order to import the data and then query one event, you can use the following commands:
```shell
curl localhost:8080/import
curl localhost:8080/events/1
```

# Technical choices:

I didn't create a standalone script, rather I created an import endpoint reading and saving the JSON and the CSV files.
I thought that having only one project would be easier as it wouldn't require two model definitions, two connection to a
db… but it ended up being complicated to maneuver. I'd consider creating a proper script and an API to ease this. 

Here are the different libraries used:

* Spring as the main framework
* Junit for testing
* AssertJ for assertions and db testing. I like the fluency of the API, and it provides easy database testing.
* Flyway for simple db migrations
* rest-assured to verify JSON content

I focused on writing my unit tests first, and then bring in the db and the http part with the integration tests. I tried
to create some kind of hexagonal architecture to have an independent domain, and no dependencies leaking into it. 

# Improvement points:

* For the endpoint retrieving the events after a certain date, the query should perform the filtering, not the code
* Proper error handling with correct status codes (404 when object not found for update for example)

# Learning points
* I'd switch the use of records to classic classes to leverage the builder pattern in order to ease objects creation
* I'd consider a less-structured db, maybe a document db such as Mongo as the data and the needs require more flexibility 
than what's currently offered by MySQL.
* I'd tried something else than JPA for the ORM part. The benefits of saving / retrieving entities easily with existing 
queries is not worth the pain of performing a relevant object to entity mapping. I'd consider using plain SQL, or maybe a NoSQL db.