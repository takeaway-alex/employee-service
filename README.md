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
sudo apt install docker
```

# Build process:

```
cd ~
git clone https://github.com/takeaway-alex/employees.git
cd employees/TAEmployeeService
mvn package
cd ../TAEntityService
mvn package
cd ../employee-ui
npm i

```

# How to run
from folder ```~/employees/employee-ui```
```
java -jar ../TAEmployeeService/target/TAEmployeeService-0.0.1.jar &
java -jar ../TAEntityService/target/TAEventService-0.0.1.jar &
npm run serve

```
