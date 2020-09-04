# Unicode SC+ Backend

## How to Run Locally
1. Prepare a local MySQL database, with ```username```, ```password``` and ```url``` consistent with those defined in 
```src/main/resources/application.properties```
2. Run the application, through IDE, or
    ```shell script
    mvn clean package spring-boot:repackage
    java -jar target/spring-boot-ops.war
    ```
    or
    ```shell script
    ./mvnw spring-boot:run
    ```
3. Now the application is running at [localhost:8080](http://localhost:8080) !
    (Optional) API Doc: http://localhost:8080/swagger-ui.html 