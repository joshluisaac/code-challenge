Mars Explorer Simulator Project
===============================

The purpose of this project is to simulate a mars explorer moving on a square tabletop, of dimensions 5 units x 5 units. The project includes the source code, sample test data, behaviour driven tests based on the specifications provided and build scripts for Maven. It also ships with a compiled version of the project making it makes it easy execute the application from terminal. The compiled version of the project resided in the `lib` directory and can be executed from `bin` using `runSimulator.sh`

The Maven will automatically download all required dependencies, compile the project, and finally run the tests specs. 

Prerequisites
-------------
- JDK 7 or higher
- Maven use `mvnw` wrapper


### Building the docs
---

Execute the command below to build API javadoc from the source code.

```shell
mvn javadoc:javadoc
```

The result is put in `/target/site/apidocs`

### Building the source code
---

```shell
mvn clean install -Dmaven.test.skip=true
```

This will compile, package and install the project. A JAR file called `marsexplorer-0.0.1-SNAPSHOT.jar` is generated
at the end of this phase and the result is copied to `target` directory.


### Running the tests with the provided data samples.
---

The tests are written in groovy using Spock framework and the source code for the tests are located in `src/test/groovy`
I choose groovy/spock because of it's simplicity and readability. Anyone looking at the tests would find it easy to figure out what's going on.

The tests has to be compiled first using this command `mvn clean test-compile`

Tests can be executed using this command `mvn clean install`


### Executing the simulator.

---


### Definition of terms
I tried to use words and file naming convention related to the problem domain.
1. A coordinate is a region/position in space that has X and Y .... 
This is represented as [Coordinate](src/main/java/com/uss/mars/exploration/Coordinate.java). 
This could also be called a `Node` or a `Point`. However, for the sake of simplicity and easier understanding i called it `Coordinate`.


### System design decisions.

1. `PLACE`,`BLOCK`,`EXPLORE` and `REPORT` are classified as the various command types. 
This is represented as an enum in [CommandType](src/main/java/com/uss/mars/exploration/CommandType.java)
2. `PLACE` and `BLOCK` have got X,Y coordinates which can be placed on the [TableTop](src/main/java/com/uss/mars/exploration/TableTop.java). 
These commands are of an [OccupantType](src/main/java/com/uss/mars/exploration/OccupantType) which implements the [TableTopOccupant](src/main/java/com/uss/mars/exploration/TableTopOccupant.java) interface. 
3. These commands are read from the terminal/console/csv file, go through come rules and validation before they end up in a
`CommandQueue`. [CommandQueueService](src/main/java/com/uss/mars/exploration/services/CommandQueueService.java) does most of this checks and validations. 
4. A couple of exceptions are thrown and logged by this service. The exceptions are custom exceptions and are relevant to the problem.
Some of these exceptions includes


