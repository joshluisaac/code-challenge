### Definition of terms

### System design decisions.

I tried to use words, terms and file naming convention related to the problem domain.
1. `PLACE`,`BLOCK`,`EXPLORE` and `REPORT` are classified as the various command types. 
This is represented as an enum constant in [CommandType](CommandType.java)
2. `PLACE` and `BLOCK` have got X,Y coordinates which can be placed on the `TableTop`. 
These commands are Occupants which implements the TableTopOccupant interface. 

### Setup requirements.

### Maven dependencies.

### Running the tests with the provided data samples.

### Executing the simulator.

