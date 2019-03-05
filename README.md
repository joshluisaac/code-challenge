Mars Explorer Simulator Project
===============================

The purpose of this project is to simulate a mars explorer moving on a square tabletop, of dimensions 5 units x 5 units. The project includes the source code, sample test data, behaviour driven tests based on the specifications provided and build scripts for Maven. It also ships with a compiled version of the project making it makes it easy execute the application from terminal. The compiled version of the project resided in the `lib` directory and can be executed from `bin` using `runSimulator.sh`

The Maven will automatically download all required dependencies, compile the project, and finally run the tests specs. 

Prerequisites
-------------
- JDK 7 or higher
- Maven use `mvnw` wrapper



Building the docs
-------------------

Execute the command below to build API javadoc from the source code.

```shell
mvn javadoc:javadoc
```

The result is put in `/target/site/apidocs`

Building the source code
-------------------------

```shell
mvn clean install -Dmaven.test.skip=true
```

This will compile, package and install the project. A JAR file called `marsexplorer-0.0.1-SNAPSHOT.jar` is generated
at the end of this phase and the result is copied to `target` directory.


Building and copying the dependencies
----------------------------------------
Run this command below to build and copy the dependencies to project `lib` directory.

```shell
mvn clean install -Dmaven.test.skip dependency:copy-dependencies
```


Running the tests with the provided data samples.
--------------------------------------------------

The tests are written in groovy using Spock framework. The source code for the tests are located in `src/test/groovy` while the sample data is located here [sampledata.txt](sampledata.txt)
I choose groovy/spock because of it's simplicity and readability. Anyone looking at the tests would find it easy to figure out what's going on.

The tests has to be compiled first using this command 

```shell 
mvn clean test-compile
```

Tests can be executed using this command 

```shell 
mvn test
```
This will run all the tests in `src/test/groovy` directory and you will see an output like this below.

```
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ marsexplorer ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.uss.mars.exploration.CommandQueueServiceTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.632 s - in com.uss.mars.exploration.CommandQueueServiceTest
[INFO] Running com.uss.mars.exploration.MovementUtilsTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in com.uss.mars.exploration.MovementUtilsTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.389 s
[INFO] Finished at: 2019-03-03T15:05:07+11:00
[INFO] ------------------------------------------------------------------------

```




Executing the simulator from Maven
------------------------------------
Run this command to execute the Simulator from maven. This command depends on `Exec Maven Plugin`.


```shell
mvn exec:exec
```

This command would yield the following log messages and output.

```
[INFO] 
[INFO] --- exec-maven-plugin:1.6.0:exec (default-cli) @ marsexplorer ---
2019-03-05 16:32:26,211 INFO  Command at the origin is positioned at (0,0)
2019-03-05 16:32:26,222 INFO  Coordinates validated successfully. All within the boundaries of the table top.
2019-03-05 16:32:26,222 INFO  Command at the head of the queue is 'PLACE'
2019-03-05 16:32:26,233 INFO  RESULT: E:(0,0) B:(0,1) (0,2) 
E:(0,0) B:(0,1) (0,2)
```


Executing the simulator from terminal
--------------------------------------
cd into the `bin` directory and run 



Definition of terms
-----------------------

I tried to use words and file naming convention related to the problem domain.
1. A coordinate is a region/position in space that has X and Y values. 
This is represented as [Coordinate](src/main/java/com/uss/mars/exploration/Coordinate.java). 
This could also be called a `Node` or a `Point`. However, for the sake of simplicity and easier understanding i called it `Coordinate`.

2. A slot is a unit or a cell with 4 sides located on the table top.


System design decisions.
----------------------------

1. `PLACE`,`BLOCK`,`EXPLORE` and `REPORT` are classified as the various command types. 
This is represented as an enum in [CommandType](src/main/java/com/uss/mars/exploration/CommandType.java)
2. `PLACE` and `BLOCK` have got X,Y coordinates which can be placed on the [TableTop](src/main/java/com/uss/mars/exploration/TableTop.java). 
These commands are of an [OccupantType](src/main/java/com/uss/mars/exploration/OccupantType) which implements the [TableTopOccupant](src/main/java/com/uss/mars/exploration/TableTopOccupant.java) interface. 
3. These commands are read from the terminal/console/csv file, go through come rules and validation before they end up in a
`CommandQueue`. [CommandQueueService](src/main/java/com/uss/mars/exploration/services/CommandQueueService.java) does most of this checks and validations. 
4. A couple of exceptions are thrown and logged by this service. The exceptions are custom exceptions and are relevant to the problem.
Some of these exceptions includes


