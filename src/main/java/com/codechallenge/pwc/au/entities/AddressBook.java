package com.codechallenge.pwc.au.entities;

import java.util.List;
import java.util.Map;

public class AddressBook {

    Map<String,String> contacts;

    public AddressBook(Map<String,String> contacts) {
        this.contacts = contacts;
    }

    public Map<String,String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String,String> contacts) {
        this.contacts = contacts;
    }
}
