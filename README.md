# employees
Employee- & Event-Service Coding Challenge
# Prerequisites:
## Ubuntu 18.04

## Java 8 sdk

```
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
```

## git

```
sudo apt install git
```

## npm

```
sudo apt install npm
```

## maven

```
sudo apt install maven
```

## docker

```
sudo apt install docker docker.io
```

# Build process:

```
cd ~
git clone https://github.com/takeaway-alex/employees.git
cd employees/employee-ui
mvn -f ../TAEmployeeService/pom.xml clean package
mvn -f ../TAEntityService/pom.xml clean package
npm i
```

# How to run
from folder ```~/employees/employee-ui```
```
sudo docker run --name postgresql -d -p 5432:5432 -e POSTGRES_PASSWORD=password postgres:11.2-alpine
sudo docker run --name rabbitmq -d -p 5672:5672 rabbitmq:3.7.12-alpine
java -jar ../TAEmployeeService/target/TAEmployeeService-0.0.1.jar & 2>&1
java -jar ../TAEntityService/target/TAEventService-0.0.1.jar & 2>&1
npm run serve &
python -mwebbrowser http://localhost:8080
```
# Notices
1) Please, DO NOT enter IDs in frontend fields
2) Use only date format "YYYY-MM-DD" (as described in spec)
3) to see events use curl:
```
curl http://localhost:8083/events
```
# Swagger
[See here](https://github.com/takeaway-alex/employees/blob/master/TAEmployeeService/swagger.yaml)
