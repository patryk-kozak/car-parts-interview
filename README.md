### Local run
In terminal, go to `docker` folder and run command:
> ```docker-compose up```

In result it should run local PostgreSQL on `5432` port.

Then launch application from top folder:
> ```./mvnw spring-boot:run -Dspring-boot.run.profiles=local```