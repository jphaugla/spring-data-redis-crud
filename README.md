# spring-data-redis-crud
Provides a quick-start example of using Redis with springBoot and the crud repository. 

## Overview
In this tutorial, a java spring boot application is run through a jar file to support typical API calls to a REDIS data layer.  A redis docker configuration is included.

## Requirements
* Docker installed on your local system, see [Docker Installation Instructions](https://docs.docker.com/engine/installation/).

* When using Docker for Mac or Docker for Windows, the default resources allocated to the linux VM running docker are 2GB RAM and 2 CPU's. Make sure to adjust these resources to meet the resource requirements for the containers you will be running. More information can be found here on adjusting the resources allocated to docker.

[Docker for mac](https://docs.docker.com/docker-for-mac/#advanced)

[Docker for windows](https://docs.docker.com/docker-for-windows/#advanced)

## Links that help!

[spring data for redis github](https://github.com/spring-projects/spring-data-examples/tree/master/redis/repositories)
[spring data for redis sample code](https://www.oodlestechnologies.com/blogs/Using-Redis-with-CrudRepository-in-Spring-Boot/)

## Getting Started
1. Prepare Docker environment-see the Prerequisites section above...
2. Pull this github into a directory
```bash
git clone https://github.com/jphaugla/spring-data-redis-crud.git
```
3. Refer to the notes for redis Docker images used but don't get too bogged down as docker compose handles everything except for a few admin steps on tomcat.
 * [https://hub.docker.com/r/bitnami/redis/](https://hub.docker.com/r/bitnami/redis/)  
4. Open terminal and change to the github home where you will see the docker-compose.yml file, then: 
```bash
docker-compose up -d
```


## The spring java code

The java code demonstrates common API actions with the data persisted in REDIS.  The java spring Boot framework mminimizes the amount of code to build and maintain this solution.  Maven is used to build the java code and the code is deployed to the tomcat server.

## To execute the code
(Alternatively, this can be run through intelli4j)

1. Compile the code
```bash
mvn package
```
2.  run the jar file.   
```bash
java -jar target/redis-0.0.1-SNAPSHOT.jar
```
3.  Test the application from a separate terminal window
  * save some "in-code" values
```bash
./scripts/saveUser.sh
```
  * save using a json file
```bash
./scripts/putUser.sh
```
  * retrieve by first and lastname	
```bash
./scripts/getByname.sh
```
  * retrieve one user by user id
```bash
./scripts/getByID.sh
```
  * delete the second value
```bash
curl http://localhost:8080/delete?id=2
```
## Redis CRUD indexing strategy
Very exciting that using the CRUD repository, a field in the java class with the Indexed annotation is treated as an index.
### User class
```bash
@RedisHash("user")
public class User {
	private @Id String id;
	private @Indexed String firstName;
	private String middleName;
	private @Indexed String lastName;
	private String roleName;
}
```
#### hash created with key of user:1
for a user with an id=1, This is stored in a Hash with a key of user:1
(this is stored in a hash and not in a json format but displaying in json)
```json
{"_class":"com.jphaugla.domain.User","id":"1","firstName":"Jason","middleName":"Paul","lastName":"Haugland","roleName":"CEO"}
```
#### Set for each unique key called user:1:idx
holds all indexed columns with the column value
Since firstName and lastName are indexed, two elements are added to this set with the key value for each index.  
```bash
user:1:idx
	user:firstName:Jason
	user:lastName:Haugland
```
#### Set with each index value  user:firstName:Jason
Then user:firstName:Jason is a set holding the user idx of each user with a first name of jason.  User 1 is Jason Haugland so 1 is in the set.  User 2 is Jason Smith so user 2 is in this set.
```bash
user:firstName:Jason
	1
	2
```
### Set with each index value user:lastName:Haugland 
Holds the user idx of each user with a last name of Haugland.  User 5 is Caterhine Haugland so user 5 is in this set.
```bash
user:lastName:Haugland
	1
	5
```
### Set with all the user ids <b>user</b> 
```bash
user
	1
	2
	5
```
