# Rate-Limited Notification Service - In JAVA.
### Author: Matias Solana Mendez

### Technologies

- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Git](https://git-scm.com)
- Spring Boot
- JPA
- Hibernate
- H2
- Maven
- Unit tests with JUnit

#### Running with Maven and Java (Maven and Java 11 installation required)

- Clone the project $ https://github.com/MattMendez/UserRegistration-Nisum.git
- Go to the project directory
  $ cd nisum/
- Generate the project jar

$ mvn clean && mvn install -DskipTests -Dspring.profiles.active=local
- Run the project with Maven $ mvn spring-boot:run

## Documentation
For testing and documentation, I recommend using [Swagger](https://swagger.io). Once the project is running, you can access it at the following link:
[Swagger-UI](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/)

Or use this curls:

curl --location 'http://localhost:8080/notifications/send' \
--header 'Content-Type: application/json' \
--data '{
"user":"user1",
"content": "content",
"topic":"Status"
}'

curl --location --request GET 'http://localhost:8080/notifications/send' \
--header 'Content-Type: application/json' \
--data '{
"user":"user1",
"content": "content",
"topic":"Status"
}'