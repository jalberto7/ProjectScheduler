# Project Scheduler

## Description
Project Scheduler is a Java-based application that calculates project schedules based on task dependencies and durations. It takes input in JSON format and determines the order of tasks, highlighting any cyclic dependencies and calculating the total project completion time.

## Features
- Parses task data from a JSON file.
- Calculates task order based on dependencies.
- Detects cyclic dependencies and invalid inputs.
- Provides estimated project completion time.
- Unit tests for reliability and accuracy.

## Technologies Used
- **Java 11**
- **Maven** – for dependency management and building
- **JUnit** – for unit testing
- **Docker** – for containerization

## Installation
### Clone the Repository
```bash
git clone https://github.com/jalberto7/ProjectScheduler.git

## Build Project 
cd ProjectScheduler
mvn clean install

## Run the application
java -jar target/ProjectScheduler-1.0.jar

## Running with Docker
### Build docker iamge
docker build -t projectscheduler:1.0 .
### Run the Docker Container
docker run -d -p 4001:4001 projectscheduler:1.0

## Testing
### Run the unit test with maven
mvn test
