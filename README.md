# Code Challenge: People Name Program 1.0


All the project was made using:

* Java 11
* Maven version 3.6.3
* Junit5
* IntelliJ IDEA IDE


## Prerequisites

* Install Java 11 or higher, download from this link [here](https://www.oracle.com/java/technologies/downloads/#java11)
* Install Maven, download from this link [here](https://maven.apache.org/download.cgi). This article shows [how configure maven on Windows](https://www.mkyong.com/maven/how-to-install-maven-in-windows/).
* Configure the JDK 11 in your machine.

## Setup

1) Unzip the project and open it in your preferred IDE.


2) Once the project is open, put the dataset file .txt in the route. Grab the file's name.

```
src/main/resources/data/
```


3) In the development IDE, open the class Constant.java and set the name to the following constant with the file's name grabbed in the previous step. 

```properties
FILE_NAME_INPUT= "data/coding-test-data.txt"
```

4) You also can set the path and file name to the output, by default is:

```properties
FILE_NAME_OUTPUT= "./output.txt"
```
This indicates that the output.txt would be in the root of the project.

5) You can also set the ```LIMIT_MODIFIED_NAME``` for combine the full names
and ```LIMIT_TOP``` for get the top of last names and firs names.

```properties
LIMIT_MODIFIED_NAME = 25
LIMIT_TOP = 10
```

## Run the project

1) Clean and install dependencies, and run the unit tests.

```bash
cd people-name-program

mvn clean install -U
```

2) Run the project as a jar:

```bash
cd people-name-program/target

java -jar code-challenge-${project.version}.jar
```
This generates the ```output.txt``` in the value defined in ```FILE_NAME_OUTPUT```
or in the root of by default (in this case in ```people-name-program/target```)

```bash
cd people-name-program/target

cat output.txt
```

Or, Run the Main.java class from ```src/main/com/code/challenge/``` 

This generates the ```output.txt``` in the value defined in ```FILE_NAME_OUTPUT``` 
or in the root of the project by default.
```bash
cd people-name-program/

cat output.txt
```

3) The file ```src/main/resources/example/running_from_command_line.txt``` has an example log 
of an execution of the program.