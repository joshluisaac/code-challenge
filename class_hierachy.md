Class hierarchy, scope and purpose document.
============================================

This is the __class hierarchy, scope and purpose document__ which is part of the code challenge deliverables.

Please note that other deliverables are also provided. This includes the following deliverables:

1. Bill of materials (BOM) document.
1. API documentation.
1. User guide document.
1. Functionality test cases.

## Package layout and structure

![Alt text][packagelayout]


## Classes and purpose

| Classes        | Purpose           |
| ------------- |:-------------:|
|`AddressBookAppCLI.java`|This is the main class for using AddressBookAppCLI and serves as the application entry point.
| `components/InputValidationParser.java`      | A utility providing command line/terminal input validation helper methods. | $1600 |
| `entities/AddressBook.java`      | A POJO object representing the address book.      |   $12 |
| `entities/Contact.java` | A POJO object representing contact details.      |    $1 |
|`persistence/AddressBookDao.java`|An implementation of `IDao.java` which is a repository for handling CRUD operations
|`exceptions/InvalidPhoneNumberException.java`|Constructs an <code>InvalidPhoneNumberException</code> with the specified message.
|`services/AddressBookService.java`|An implementation of `IAddressBookService` that contains methods which perform operations with one or more models and transactions.
|`services/AddressBookUnionService.java`|An implementation of `IAddressBookUnionService.java` that takes two address books and resolves the uniqueness between them.
|`utils/JsonUtils.java`|A `Gson` wrapper class providing forwarding and helper methods for JSON utilities.
|`utils/RegexUtils.java`|A utility providing regex helper methods.
||




[packagelayout]: package_layout.png "Logo Title Text 2"