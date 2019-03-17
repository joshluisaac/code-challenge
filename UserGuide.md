User Guide Document.
==========================

This is the __user guide document__ which is part of the code challenge deliverables. Please note that other
deliverables are also provided. This includes the following:

1. Bill of materials (BOM) document.
1. Class hierarchy, scope and purpose.
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

`java <-classpath|-cp> [-s] <contact name> <contact number>`

or

`java <-classpath|-cp> [--store] <contact name> <contact number>`

__Argument specification__:

`-s or --store`: These are flags which specifies the mode in which you want to execute the program.

`contact name`: The contact owner name.

`contact number`: the contact number.

__Execution script__
```bash
java -cp config:dist/code-challenge-0.0.1-SNAPSHOT.jar com.codechallenge.pwc.au.AddressBookAppCLI -s Joshua 0479109809
```

or

```bash
java -jar dist/code-challenge-0.0.1-SNAPSHOT.jar --store Joshua 0479109809
```


__Please note the following about the above command__

The raw input passed in from command line is first parsed and validated by the [InputValidationParser](src/main/java/com/codechallenge/pwc/au/components/InputValidationParser.java).
> 1. `--store Joshua 0479109809`  is space separated.
> 2. `0479109809` contact numbers can only contain numeric digits. That is, anything within this regex bounds `^[0-9]+$`. Anything outside this bounds would throw a [InvalidPhoneNumberException](src/main/java/com/codechallenge/pwc/au/exceptions/InvalidPhoneNumberException.java).



Executing any of the above command will yield the following logs written to terminal.
Please notice this in the `Joshua 0479109809` last line. This was the contact number which was added.

```
$ java -jar dist/code-challenge-0.0.1-SNAPSHOT.jar --store Joshua 0479109809
2019-03-15 21:23:14,358 INFO  Running address book app in store mode
2019-03-15 21:23:14,416 INFO  Added new contact Contact{name='Joshua', phoneNumber='0479109809'}
2019-03-15 21:23:14,416 INFO  Displaying contact numbers ordered by name.
Joshua 0479109809
```

#### Consecutive batch execution.

Executing this command in batches will persist each individual contact to database and display the list
of contacts in ascending order by name.

```
java -jar dist/code-challenge-0.0.1-SNAPSHOT.jar --store Adam 04897654367 &&
java -jar dist/code-challenge-0.0.1-SNAPSHOT.jar --store Zoe 09864567 &&
java -jar dist/code-challenge-0.0.1-SNAPSHOT.jar --store Jenny 1890876
```

You will see the following logged to terminal.
```
2019-03-15 21:47:38,799 INFO  Running address book app in store mode
2019-03-15 21:47:38,859 INFO  Added new contact Contact{name='Jenny', phoneNumber='1890876'}
2019-03-15 21:47:38,859 INFO  Displaying contact numbers ordered by name.
Adam 04897654367
Jenny 1890876
Joshua 0479109809
Zoe 09864567
```


Executing the app in union mode
-------------------------------
__Execution syntax__

`java -jar <classpath>  [-u] {name:phoneNumber}`

__Execution script__

```bash
java -jar target/code-challenge-0.0.1-SNAPSHOT.jar --union "{Jenny:098765667,Asha:908654}"
```

or

```bash
java -jar target/code-challenge-0.0.1-SNAPSHOT.jar -u "{Jenny:098765667,Asha:908654}"
```

__Argument specification__:

`-u or --union`: These are flags which specifies the mode in which you want to execute the program.

`"{Jenny:098765667,Asha:908654}"`: JSON formatted address book 2.

__Please note the following about the above command__

The raw input passed in from command line is first parsed and validated by the [InputValidationParser](src/main/java/com/codechallenge/pwc/au/components/InputValidationParser.java).
> 1. `"{Jenny:098765667,Asha:908654}"`  is enclosed in double quotes.
> 2. It contains `name:phoneNumber` pairs.
> 3. Each pair is separated by a comma.


You will see the following logged to terminal on successful execution.

```
$ java -jar target/code-challenge-0.0.1-SNAPSHOT.jar --union "{Jenny:098765667,Asha:908654}"
2019-03-15 22:00:28,783 INFO  Running address book app in union mode
2019-03-15 22:00:28,842 INFO  Displaying unique entries
Book 1/Book 2: {Adam=04897654367, Asha=908654, Joshua=0479109809, Zoe=09864567}

```
