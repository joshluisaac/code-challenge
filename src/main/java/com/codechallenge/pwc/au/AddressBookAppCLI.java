package com.codechallenge.pwc.au;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.utils.JsonUtils;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AddressBookAppCLI {

    private final AddressBookService addressBookService;

    private static final String ADDRESS_BOOK_DATA_BASE = "book.json";

    public AddressBookAppCLI(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
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


    public static void main(String[] args) throws Exception {

        Contact contact = new Contact("JoshuA", "0479109802");

        /*Deserialize the JSON file into Java objects*/
        SortedMap<String, String> database = new JsonUtils().fromJson(new FileReader(new File(ADDRESS_BOOK_DATA_BASE)), new TypeToken<SortedMap<String, String>>() {
        }.getType());

        SortedMap<String, String> existingContacts = new TreeMap<>(String::compareToIgnoreCase);

        for (SortedMap.Entry<String, String> entry : database.entrySet())
            existingContacts.put(entry.getKey(), entry.getValue());

        AddressBookAppCLI cli = new AddressBookAppCLI(new AddressBookService(new AddressBookDao(), new AddressBook(existingContacts)));
        cli.execute(contact);

        cli.displayAddressBook();

        cli.writeToCache(new File(ADDRESS_BOOK_DATA_BASE), new JsonUtils().toJson(cli.getAddressBook()));


    }

}
