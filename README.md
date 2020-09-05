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

## Authorization Mechanism
- All requests require authorization, except ```/api/auth``` and those related with Swagger
- By posting to ```/api/auth/login```, you can get a JWT string, like ```eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0VXNlcjIiLCJpYXQiOjE1OTkyNjU4ODQsImV4cCI6MTU5OTI2Njc4NH0.AtS6yfxNHQD72cz6zR4hBJZVDn6ZkqJQcBsfcL9fS1PHbzJocd0_niHGYBnw-heosYXiGMRhZeF_s9SNJUpwQsoTks4eYPqV-vwtoDktUp_rlv0kePSsXRdW_OlRZLU6p6XnliKfIG4YalJSR0HgnhN0uejO8KFXNb64EmBRloUpxWtcck3ht03JGsY9UavzA9AbuWTmSKz_EiB7fzLUNzN8izhegc0j8OdIXldc3UhfEeZg8dV8jx7Y7LYr7A8avKBeAMRV05LgtljPO_Hug6lSxRasRqMMpOlKi3Y7KuPy63yYiNXARS7ilszuobISU2_mVBtrlaJ7ea0-L9_Lgg```
- You should add the header: ```Authorization: Bearer <token>``` with the JWT token, to your requests, or you'll get 403.
- The token expires in 900000 ms = 15 min. 