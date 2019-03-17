package com.codechallenge.pwc.au;

import com.codechallenge.pwc.au.components.InputValidationParser;
import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.persistence.IDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.services.AddressBookUnionService;
import com.codechallenge.pwc.au.services.IAddressBookUnionService;
import com.codechallenge.pwc.au.utils.JsonUtils;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;


public class AddressBookAppCLI {

    private static final Logger LOG = LoggerFactory.getLogger(AddressBookAppCLI.class);

    private final AddressBookService addressBookService;
    private final IAddressBookUnionService unionService;


    public AddressBookAppCLI(AddressBookService addressBookService,IAddressBookUnionService unionService) {
        this.addressBookService = addressBookService;
        this.unionService = unionService;
    }

    /**
     * A forwarding method which is a wrapper around {@link AddressBookService#saveContact(Contact)}
     * This executes the service business logic persists a newly added contact to memory pending writes to data store.
     */
    public void execute(final Contact contact) {
        addressBookService.saveContact(contact);
    }

    /**
     * A forwarding method which is a wrapper around {@link AddressBookService#writeToCache(File, String)}
     * Writes an address book to file on disk. This is done for reuse between successive runs.
     */
    public void writeToCache(final File file, String jsonContent) throws IOException {
        addressBookService.writeToCache(file, jsonContent);
    }

    /**
     * A forwarding method which is a wrapper around {@link AddressBookService#displayAddressBook()}
     * Displays the contents of the address book.
     */
    public void displayAddressBook() {
        addressBookService.displayAddressBook();
    }

    /**
     * A forwarding method which is a wrapper around {@link AddressBookService#getAddressBook()}
     * Retrieves an address book.
     *
     * @return the address book as a {@link Map}
     */
    public Map<String, String> getAddressBook() {
        return addressBookService.getAddressBook();
    }


    /**
     * A forwarding method which is a wrapper around {@link AddressBookUnionService#union(Map, Map)}
     * Resolves the differences between two maps.
     *
     * @return a map which is what is unique between those two maps.
     */
    public SortedMap<String, String> executeUnion(Map<String, String> book1, Map<String, String> book2){
        LOG.info("Displaying unique entries");
        return unionService.union(book1,book2);
    }

    public static void displayUsage(){
        System.out.println("Usage instruction.");
        System.out.println("-s, --store execute program in store mode.");
        System.out.println("-u, --union execute program in union mode.");
        System.out.println("In store contact mode execute: " + "java -jar <classpath>  [-s] <contact name> <contact number>");
        System.out.println("In union mode execute: " + "java -jar <classpath>  [-u] {name:phoneNumber}");
        System.out.println("Usage example: java -jar dist/code-challenge-0.0.1-SNAPSHOT.jar --store Jenny 0776447883");
        System.out.println("Usage example: java -jar target/code-challenge-0.0.1-SNAPSHOT.jar --union \"{Jenny:098765667,Asha:908654}\"\n");
    }


    public static void main(String[] args) throws Exception {
        boolean storageMode = false;
        boolean unionMode = false;

        if (args[0].equalsIgnoreCase("-s") || args[0].equalsIgnoreCase("--store")){
            LOG.info("Running address book app in store mode");
            storageMode = true;

        } else if (args[0].equalsIgnoreCase("-u") || args[0].equalsIgnoreCase("--union")) {
            LOG.info("Running address book app in union mode");
            unionMode = true;

        } else if (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")){
            displayUsage();
            return;
        } else {
            displayUsage();
            return;
        }

        if (storageMode) {
            String contactName = args[1];
            String contactNumber = args[2];

            Optional<Contact> maybeContact = Optional.of(new Contact(contactName.trim(), contactNumber.trim()));
            Contact contact = maybeContact.orElse(new Contact());

            IDao dataAccess = new AddressBookDao();
            AddressBook addressBook = new AddressBook();
            AddressBookService service = new AddressBookService(dataAccess, addressBook);
            IAddressBookUnionService unionService = new AddressBookUnionService();
            AddressBookAppCLI cli = new AddressBookAppCLI(service,unionService);

            cli.execute(contact);
            cli.displayAddressBook();
            cli.writeToCache(new File(addressBook.getBookName()), new JsonUtils().toJson(cli.getAddressBook()));
        }

        else if (unionMode){
            String addressBook2RawInput = args[1];

            /*Parse and validate input string*/
            boolean isValidJson = InputValidationParser.isValidJson(addressBook2RawInput);

            if (isValidJson) {
                SortedMap<String, String> book2 = new JsonUtils().fromJson(addressBook2RawInput, new TypeToken<SortedMap<String, String>>() {}.getType());
                AddressBookService service = new AddressBookService(new AddressBookDao(), new AddressBook());
                IAddressBookUnionService unionService = new AddressBookUnionService();
                AddressBookAppCLI cli = new AddressBookAppCLI(service,unionService);
                SortedMap<String, String> union = cli.executeUnion(cli.getAddressBook(),book2);
                System.out.println(union.toString());
            } else {
                LOG.error("{} is not a valid JSON", addressBook2RawInput);
            }




        }




    }

}
