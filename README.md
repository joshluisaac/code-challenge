### Setup requirements.


---

### Maven dependencies.
---

#### Building the docs
Execute the command below to build API javadoc from the source code.

```shell
mvn javadoc:javadoc
```

The result is put in `/target/site/apidocs`

#### Building the source code

```shell
mvn clean install
```

This will compile, test, package and install the project. A JAR file called `marsexplorer-0.0.1-SNAPSHOT.jar` is generated
at the end of this phase and the result is copied to `target` directory.


### Running the tests with the provided data samples.
---



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


