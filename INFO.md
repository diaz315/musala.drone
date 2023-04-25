## Instructions

1. Open a terminal and navigate to the root folder of your project.

2. To run the project, execute the following command:
   .\mvnw.cmd spring-boot:run

3. Alternatively, you can compile and package the application into a jar file by running the following command:
   ./mvnw.cmd clean package

4. Then, execute the jar file from the console (make sure you have Java 20  installed):
   java -jar target/drone-0.0.1-SNAPSHOT.jar

5. Finally, you can access the Swagger interface at http://localhost:8080/swagger-ui/index.html and test the services.

6. To run unit tests, open a terminal and navigate to the root folder of your project, then run the following command:
   .\mvnw.cmd test

