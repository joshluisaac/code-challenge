package com.codechallenge.pwc.au.services;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.IDao;
import com.codechallenge.pwc.au.utils.JsonUtils;

import java.util.Map;

public class AddressBookService {


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
        System.out.println(new JsonUtils().toJson(existingContacts));
    }

    void displayAddressBook(){
        getAddressBook();
        //

    }

    public boolean contactNumberExists(Map<String,String> map, String key){
        return map.containsKey(key);
    }


}
