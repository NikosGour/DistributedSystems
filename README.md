# Getting Started

### Docker Container for MySQL

```shell
docker run --name distributed_systems_database -p 3306:3306 -e MYSQL_ROOT_PASSWORD="nikos2002" -e MYSQL_DATABASE="SpringBootDB" -d mysql:latest
 ```

## Run the application

 ```shell
 ./mvnw package -Dmaven.test.skip
 ```

 ```shell
 java -jar target/DistributedSystemsAssignment-0.0.1-SNAPSHOT.jar
 ```