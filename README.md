# hateos-sample

## Content
- Sample data in *assignment.sql*
- Postman json collection in *spring-hateos.postman_collection.json*

## Prequisite :
- Java 1.8
- Maven 4.0.0
- Mysql version 8.0.19

## Steps to Build and Run
- Clone the project in local machine
- Import sample data into mysql from *assignment.sql*
- Change drectory into the project and run *./gradlew bootRun*
- Above command will download dependencies, build the code and deploy the build in embedded tomcat.
- Import postman colllection from *spring-hateos.postman_collection.json* and start testing.
