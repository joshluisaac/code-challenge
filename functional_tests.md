Functionality test cases document.
============================================

This is the __Functionality test cases document__ which is part of the code challenge deliverables. This document
 details the test package structure and the purpose of each test, the test coverage and tests logs.

 Please note that other deliverables are also provided. This includes the following deliverables:

1. Bill of materials (BOM) document.
1. API documentation.
1. User guide document.
1. Class hierarchy, scope and purpose document.


## Tests package layout and structure

![Alt text][functional_tests]

## Test classes and purpose

| Test classes        | Purpose           |
| ------------- |:-------------:|
|`AddressBookAppCLITest`|Integration test for main class `AddressBookAppCLI`
|`AddressBookServiceTest`|Unit test for `AddressBookService`
|`AddressBookUnionServiceTest`| Unit test for `AddressBookUnionService`
|`RegexUtilsTest`|Unit test for `RegexUtils`
|`InputValidationParserTest`|Unit test for `InputValidationParser`


## Test coverage

Test coverage of `AddressBookAppCLITest.java`

1. display_usage_when_invalid_flag_is_passed_integration_test()
1. execute_store_command_integration_test_v1()
1. execute_store_command_integration_test_v2()
1. execute_union_command_integration_test_v1()
1. execute_union_command_integration_test_v2()

Test coverage of `AddressBookServiceTest.java`

1. cache_should_increment_when_i_add_a_new_contact()
1. cache_should_not_increase_when_i_add_an_existing_contact()

Test coverage of `AddressBookUnionServiceTest.java`

1. the_union_of_two_address_books_should_produce_unique_map()


Test coverage of `RegexUtilsTest.java`

1. i_expect_false_when_phone_number_fails_regex()
1. i_expect_true_when_phone_number_passes_regex()

Test coverage of `InputValidationParserTest.java`

1. throw_JsonSyntaxException_when_Json_is_invalid()
1. return_true_when_JSON_is_valid()
1. return_false_when_phone_number_is_invalid()
1. return_true_when_phone_number_is_valid()


## Test logs
```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running unit.RegexUtilsTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.029 sec
Running unit.AddressBookUnionServiceTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.028 sec
Running unit.AddressBookServiceTest
2019-03-15 05:11:53,565 INFO  Added new contact Contact{name='Simeon', phoneNumber='0479109809'}
2019-03-15 05:11:53,567 INFO  Updated existing contact Contact{name='Simeon', phoneNumber='0479109809'}
2019-03-15 05:11:53,568 INFO  Added new contact Contact{name='Mark', phoneNumber='0479109809'}
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.121 sec
Running unit.InputValidationParserTest
2019-03-15 05:11:53,570 ERROR {joshua:,zoe:87655456787} is not a valid JSON formatted string.
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 sec

Results :

Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
```



[functional_tests]: functional_tests.png "Logo Title Text 2"