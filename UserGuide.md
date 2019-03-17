User Guide Document.
==========================

This is the user guide document which is part of the code challenge deliverables. Please note that other
deliverables are also provided. This includes the following:

1. Functionality
2. Spefic...




Executing the app in storage mode
---------------------------------
__Execution syntax__

`java <classpath args> [-s] <contact name> <contact number>`

or

`java <classpath args> [--store] <contact name> <contact number>`

__Argument definition__:

`-s or --store`: These are flags which specifies the mode in which you want to execute the program.

`contact name`:

`contact number`:

```bash
java -classpath config:target/code-challenge-0.0.1-SNAPSHOT.jar com.codechallenge.pwc.au.AddressBookAppCLI -s sam 09876548997
```

or

```bash
java -jar target/code-challenge-0.0.1-SNAPSHOT.jar --store Jenny 0776447883
```