# Practice client server

### Build docker image
In order to build the docker images and storage locally use the following:

`./gradlew bootBuildImage --no-publishImage`


### Run using docker
There is a docker's compose file to test the application

`docker-compose -f ./compose-prod.yaml up`