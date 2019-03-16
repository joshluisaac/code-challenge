package com.codechallenge.pwc.au;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class AddressBookAppCLI {

    private final AddressBookService addressBookService;

    public AddressBookAppCLI(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }


    public void execute(final Contact contact){
        addressBookService.saveContact(contact);
    }

    public void writeToCache(final File file, String jsonContent) throws IOException {
        addressBookService.writeToCache(file, jsonContent);
    }

    public void displayAddressBook(){
        addressBookService.displayAddressBook();
    }

    public Map<String,String>  getAddressBook(){
        return addressBookService.getAddressBook();
    }



    public static void main(String[] args) throws Exception {
        Contact contact = new Contact("Joshua","0479109809");
        Contact contact2 = new Contact("Zoe","0479109800");
        Contact contact3 = new Contact("Jeff","0479109801");
        Contact contact4 = new Contact("Henry","0479109802");


        Map<String,String> existingContacts = new TreeMap<>(String::compareToIgnoreCase);

        AddressBookAppCLI cli = new AddressBookAppCLI(new AddressBookService(new AddressBookDao(),new AddressBook(existingContacts)));
        cli.execute(contact);
        cli.execute(contact2);
        cli.execute(contact3);
        cli.execute(contact4);

        cli.displayAddressBook();

        cli.writeToCache(new File("book.json"),new JsonUtils().toJson(cli.getAddressBook()));


    }

}
