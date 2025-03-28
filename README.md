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

## Enhancement Update March 28, 2025
## How to Use the Application
## Once the application is running, follow these steps:

Example Test Flow
Steps to Test

1. Create Project Plans
  ==
  Enter your choice: 1
  Enter Project Plan Name: ProjectPlan1
  ==
  Enter your choice: 1
  Enter Project Plan Name: ProjectPlan2

2. Add Tasks to Project Plans
  ==
  Enter your choice: 2
  Available Project Plans:
  1. ProjectPlan1
  2. ProjectPlan2
  Select a Project Plan ID: 1
  Enter Task Name: Task1
  Enter Task Duration (in days): 5
  Enter Start Date (YYYY-MM-DD): 2025-04-01
  Enter Number of Dependencies: 0
  ==
  Enter your choice: 2
  Select a Project Plan ID: 1
  Enter Task Name: Task2
  Enter Task Duration (in days): 3
  Enter Start Date (YYYY-MM-DD): 2025-04-05
  Enter Number of Dependencies: 1
  Enter Dependency Task ID: 1
  ==
  Enter your choice: 2
  Select a Project Plan ID: 2
  Enter Task Name: Task3
  Enter Task Duration (in days): 7
  Enter Start Date (YYYY-MM-DD): 2025-04-02
  Enter Number of Dependencies: 0

3. Calculate Project Schedules
  ==
  Enter your choice: 3
  Available Project Plans:
  1. ProjectPlan1
  2. ProjectPlan2
  Select a Project Plan ID: 1
  ==
  Calculating schedule for ProjectPlan1...
  Task 1: Task1
    Start Date: 2025-04-01
    Duration: 5 days
    End Date: 2025-04-06
  
  Task 2: Task2
    Start Date: 2025-04-05
    Duration: 3 days
    End Date: 2025-04-08
    Dependencies: [1]
  
  Project Plan: ProjectPlan1
  Start Date: 2025-04-01
  End Date: 2025-04-08
  Total Duration: 7 days
