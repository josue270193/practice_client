# Practice client server

## Components
- Java 21
- Spring boot 3
- PostgreSQL
- Docker

### Database script
This project uses flyway to execute SQL script to create the database schema, you are free to check it out in any module.

`${MODULE}/src/main/resources/db/migration/postgresql`

### Postman
There is a folder called postman, in there, are multiple collection for postman to be used when the modules are running.

### Build docker image
In order to build the docker images and storage locally use the following:

`./gradlew bootBuildImage --no-publishImage`


### Run using docker
There is a docker's compose file to test the application

`docker-compose -f ./compose-prod.yaml up`