
# Axon Library
## Introduction

This is a sample application with the purpose of showing the capabilities of Axon Framework.

The Library Application makes use of the following design patterns:
- Domain Driven Design
- CQRS
- Event Sourcing

## Domain
Axon Library concerns itself with the book library domain. The application consists of 1 aggregates: book 

## Technical Details
We have tried to keep the application self-contained. It is built with Spring Boot and therefore does not require you to have Tomcat installed.
Running the application on a single node does not require you to install any dependencies. The storage of data is all done in memory and does not require you to run an external data store.

Technologies used
- Spring Boot
- Axon Framework

## Building and Running
In order to start the project please follow the current steps: 
* `mvn clean install`
* `java -jar .\target\axon-2.1.3.RELEASE.jar`
Download and run the Axon Server
* `java -jar axonserver-4.2.2.jar`

Usefull links

Axon Library project will run on: 
localhost:8080 port

Axon server will run on 8024 port

H2 Console is available at: 
http://localhost:8080/h2-console/

Axon dashboard port
http://localhost:8024/

Available functionalities
Creating new Books
Replay events related to book domain