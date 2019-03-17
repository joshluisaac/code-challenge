User Guide Document.
==========================

This is the __user guide document__ which is part of the code challenge deliverables. Please note that other
deliverables are also provided. This includes the following:

1. Bill of materials (BOM) document.
1. Functionality
2. Spefic...


The application can be executed in one of two modes depending on the flag which was passed in.
These modes are controlled by certain flags.

`-s or --store` : Indicates that you want to run the program in storage mode. In storage mode, the name and phone number is
persisted to `book.json`. This serves as a persistent database object which is then used for successive runs.

`-u or --union`

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

Please note the following about the above command
>> 1. `--store Jenny 0776447883`  is space separated.
>> 2. `0776447883` contact numbers can only contain numeric digits. That is, {0-9}



Executing any of the above command will yield the following logs written to terminal

```

```