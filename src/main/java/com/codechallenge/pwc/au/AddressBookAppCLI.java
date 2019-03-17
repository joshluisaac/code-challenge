package com.codechallenge.pwc.au;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.services.AddressBookUnionService;
import com.codechallenge.pwc.au.services.IAddressBookUnionService;
import com.codechallenge.pwc.au.utils.JsonUtils;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

public class AddressBookAppCLI {

    private static final Logger LOG = LoggerFactory.getLogger(AddressBookAppCLI.class);

    private final AddressBookService addressBookService;
    private final IAddressBookUnionService unionService;

    private static final String ADDRESS_BOOK_DATA_BASE = "book.json";

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
     */
    public Map<String, String> getAddressBook() {
        return addressBookService.getAddressBook();
    }

    public static void displayUsage(){

    }

    /*Deserialize the JSON file into Java objects*/
    public static SortedMap<String, String> getDatabase() throws IOException{
        SortedMap<String, String> database = new JsonUtils().fromJson(new FileReader(new File(ADDRESS_BOOK_DATA_BASE)), new TypeToken<TreeMap<String, String>>() {
        }.getType());
        SortedMap<String, String> existingContacts = new TreeMap<>(String::compareToIgnoreCase);
        for (SortedMap.Entry<String, String> entry : database.entrySet())
            existingContacts.put(entry.getKey(), entry.getValue());
        return existingContacts;
    }

    public SortedMap<String, String> executeUnion(Map<String, String> book1, Map<String, String> book2){
        return unionService.union(book1,book2);
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

            AddressBookService service = new AddressBookService(new AddressBookDao(), new AddressBook(AddressBookAppCLI.getDatabase()));
            IAddressBookUnionService unionService = new AddressBookUnionService();
            AddressBookAppCLI cli = new AddressBookAppCLI(service,unionService);

            cli.execute(contact);
            cli.displayAddressBook();
            cli.writeToCache(new File(ADDRESS_BOOK_DATA_BASE), new JsonUtils().toJson(cli.getAddressBook()));
        }

        else if (unionMode){
            String addressBook2RawInput = args[1];

            SortedMap<String, String> book2 = new JsonUtils().fromJson(addressBook2RawInput, new TypeToken<SortedMap<String, String>>() {}.getType());

        }




    }

}
