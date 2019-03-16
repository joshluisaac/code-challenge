package com.codechallenge.pwc.au.services;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AddressBookService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressBookService.class);


    private final IDao dao;
    private final AddressBook addressBook;

    public AddressBookService(final IDao dao,final AddressBook addressBook) {
        this.dao = dao;
        this.addressBook = addressBook;
    }

    public void saveContact(final Contact contact) {
        Map<String,String> existingContacts = getAddressBook();
        int size = existingContacts.size();
        if (size > 0){
            if(contactNumberExists(existingContacts,contact.getName())){
                dao.update(existingContacts,contact);
                LOG.info("Updated existing contact");
            } else {
                dao.add(existingContacts,contact);
                LOG.info("Added new contact");
            }
        } else {
            dao.add(existingContacts,contact);
            LOG.info("Added new contact");
        }

    }

    public void displayAddressBook(){
        LOG.info("Displaying contact numbers ordered by name.");
        for(Map.Entry<String, String> entry : getAddressBook().entrySet()) {
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
    }

    public void writeToCache(final File file, final String jsonContent) throws IOException {
        dao.writeToDataStore(file, jsonContent);
    }

    public boolean contactNumberExists(Map<String,String> map, String key){
        return map.containsKey(key);
    }

    public Map<String,String> getAddressBook(){
        return addressBook.getContacts();
    }


}
