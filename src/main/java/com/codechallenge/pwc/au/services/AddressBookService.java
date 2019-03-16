package com.codechallenge.pwc.au.services;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.IDao;
import com.codechallenge.pwc.au.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.nio.file.Files;
import java.util.Map;

public class AddressBookService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressBookService.class);


    private final IDao dao;
    private final AddressBook addressBook;

    public AddressBookService(final IDao dao,final AddressBook addressBook) {
        this.dao = dao;
        this.addressBook = addressBook;
    }

    public Map<String,String> getAddressBook(){
        return addressBook.getContacts();
    }

    public void saveContact(final Contact contact) {
        Map<String,String> existingContacts = getAddressBook();
        int size = existingContacts.size();
        if (size > 0){
            if(contactNumberExists(existingContacts,contact.getName())){
                dao.update(existingContacts,contact);
            } else {
                dao.add(existingContacts,contact);
            }
        } else {
            dao.add(existingContacts,contact);
        }

        LOG.info("Saved contact");

    }

    public void displayAddressBook(){
        for(Map.Entry<String, String> entry : getAddressBook().entrySet()) {
            LOG.info("{} {}", entry.getKey(), entry.getValue());
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
    }

    public void writeToCache(final File file, final String jsonContent) throws IOException {
        dao.writeToDataStore(file, jsonContent);
    }

    public boolean contactNumberExists(Map<String,String> map, String key){
        return map.containsKey(key);
    }


}
