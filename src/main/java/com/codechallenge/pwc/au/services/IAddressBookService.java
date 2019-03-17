package com.codechallenge.pwc.au.services;

import com.codechallenge.pwc.au.entities.Contact;

import java.io.File;
import java.io.IOException;

public interface IAddressBookService {

    public void saveContact(final Contact contact);
    public void displayAddressBook();
    public void writeToCache(final File file, final String jsonContent) throws IOException;
}
