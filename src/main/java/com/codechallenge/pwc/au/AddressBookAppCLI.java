package com.codechallenge.pwc.au;

import com.codechallenge.pwc.au.components.InputValidationParser;
import com.codechallenge.pwc.au.components.LocalStorageStreamStrategy;
import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.AddressBookDatabase;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.exceptions.InvalidPhoneNumberException;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.persistence.IDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.services.AddressBookUnionService;
import com.codechallenge.pwc.au.services.IAddressBookService;
import com.codechallenge.pwc.au.services.IAddressBookUnionService;
import com.codechallenge.pwc.au.utils.JsonUtils;
import com.codechallenge.pwc.au.utils.MapperUtils;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * This is the main class for using AddressBookAppCLI and serves as the
 * application entry point.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */

public class AddressBookAppCLI {

    private static final Logger LOG = LoggerFactory.getLogger(AddressBookAppCLI.class);
    private final IAddressBookService addressBookService;
    private final IAddressBookUnionService unionService;

    public AddressBookAppCLI(IAddressBookService addressBookService, IAddressBookUnionService unionService) {
        this.addressBookService = addressBookService;
        this.unionService = unionService;
    }

    /**
     * A forwarding method which is a wrapper around
     * {@link AddressBookService#saveContact(Contact)} This executes the service
     * business logic persists a newly added contact to memory pending writes to
     * data store.
     *
     * @param contact the new/existing contact object
     */
    public void execute(final Contact contact) {
        addressBookService.saveContact(contact);
    }

    /**
     * A forwarding method which is a wrapper around
     * {@link AddressBookService#writeToCache(File, String)} Writes an address book
     * to file on disk. This is done for reuse between successive runs.
     *
     * @param file        cache file
     * @param jsonContent json formatted document
     *
     * @throws IOException throws exception if file is not found
     */
    public void writeToCache(final File file, String jsonContent) throws IOException {
        addressBookService.writeToCache(file, jsonContent);
    }

    /**
     * A forwarding method which is a wrapper around
     * {@link AddressBookService#displayAddressBook()} Displays the contents of the
     * address book.
     */
    public void displayAddressBook() {
        addressBookService.displayAddressBook();
    }

    /**
     * A forwarding method which is a wrapper around
     * {@link AddressBookService#getAddressBook()} Retrieves an address book.
     *
     * @return the address book as a {@link Map}
     */
    public Map<String, String> getAddressBook() {
        return addressBookService.getAddressBook();
    }

    /**
     * A forwarding method which is a wrapper around
     * {@link AddressBookUnionService#union(Map, Map)} Resolves the differences
     * between two maps.
     *
     * @param book1 address book 1
     * @param book2 address book 2
     *
     * @return a map which is what is unique between those two maps.
     */
    public SortedMap<String, String> executeUnion(Map<String, String> book1, Map<String, String> book2) {
        LOG.info("Displaying unique entries");
        return unionService.union(book1, book2);
    }



    public static void displayUsage() {
        System.out.println("Usage instruction.");
        System.out.println("-s, --store execute program in store mode.");
        System.out.println("-u, --union execute program in union mode.");
        System.out.println(
                "In store contact mode execute: " + "java -jar <classpath>  [-s] <contact name> <contact number>");
        System.out.println("In union mode execute: " + "java -jar <classpath>  [-u] {name:phoneNumber}");
        System.out.println("Usage example: java -jar dist/code-challenge-0.0.1-SNAPSHOT.jar --store Jenny 0776447883");
        System.out.println(
                "Usage example: java -jar target/code-challenge-0.0.1-SNAPSHOT.jar --union \"{Jenny:098765667,Asha:908654}\"\n");
    }

    public static void main(String[] args) throws Exception {
        boolean storageMode = false;
        boolean unionMode = false;

        if(args.length == 0) {
            displayUsage();
            return;
        }

        if (args[0].equalsIgnoreCase("-s") || args[0].equalsIgnoreCase("--store")) {
            LOG.info("Running address book app in store mode");
            storageMode = true;

        } else if (args[0].equalsIgnoreCase("-u") || args[0].equalsIgnoreCase("--union")) {
            LOG.info("Running address book app in union mode");
            unionMode = true;

        } else if (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")) {
            displayUsage();
            return;
        } else {
            displayUsage();
            return;
        }

        if (storageMode) {
            String contactName = args[1];
            String contactNumber = args[2];

            //validate phone number
            boolean isValidPhoneNo = InputValidationParser.isValidPhoneNumber(contactNumber);

            if (isValidPhoneNo) {
                //trim leading and trailing spaces in phone numbers
                Optional<Contact> maybeContact = Optional.of(new Contact(contactName.trim(), contactNumber.trim()));
                Contact contact = maybeContact.orElse(new Contact());

                //return address book from stream
                AddressBook addressBook = new AddressBook(new LocalStorageStreamStrategy(new FileInputStream(new File(AddressBookDatabase.DATABASE))));

                //construct CLI object
                AddressBookAppCLI cli = new AddressBookAppCLI(new AddressBookService(new AddressBookDao(), addressBook), new AddressBookUnionService());
                cli.execute(contact);
                cli.displayAddressBook();
                cli.writeToCache(new File(AddressBookDatabase.DATABASE), new JsonUtils().toJson(cli.getAddressBook()));
            } else {
                LOG.error("{} is not a valid phone number.", contactNumber);
                throw new InvalidPhoneNumberException(
                        "Phone number is invalid. Please check phone number contains only numbers (0-9)");
            }

        }

        else if (unionMode) {
            String addressBook2RawInput = args[1];

            /* Parse and validate input string */
            boolean isValidJson = InputValidationParser.isValidJson(addressBook2RawInput);

            if (isValidJson) {
                IAddressBookService service = new AddressBookService(new AddressBookDao(), new AddressBook(new LocalStorageStreamStrategy(new FileInputStream(new File(AddressBookDatabase.DATABASE)))));

                //construct CLI object
                AddressBookAppCLI cli = new AddressBookAppCLI(service, new AddressBookUnionService());
                SortedMap<String, String> union = cli.executeUnion(cli.getAddressBook(), MapperUtils.remapBook2(addressBook2RawInput));
                System.out.println("Book 1/Book 2: " + union.toString());
            } else {
                LOG.error("{} is not a valid JSON formatted string.", addressBook2RawInput);
            }
        }
    }

}
