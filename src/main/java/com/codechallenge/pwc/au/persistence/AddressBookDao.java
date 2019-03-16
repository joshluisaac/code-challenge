package com.codechallenge.pwc.au.persistence;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;

import java.util.List;
import java.util.Map;

public class AddressBookDao implements IDao {


    @Override
    public void update(final Map<String,String> map, final Contact contact){
        map.replace(contact.getName(),contact.getPhoneNumber());
    }

    @Override
    public void add(final Map<String,String> map, final Contact contact) {
        map.put(contact.getName(),contact.getPhoneNumber());
    }


    public List<Contact> queryAddressBook(){
        return null;
    }


    public void mergeAddress(final AddressBook addressBook) {

    }
}
