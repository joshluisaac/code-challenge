package com.codechallenge.pwc.au.persistence;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;

import java.util.Map;

public interface IDao {

    //void save(final Contact contact);
    void update(final Map<String,String> map, final Contact contact);
    void add(final Map<String,String> map, final Contact contact);
    //void mergeAddress(final AddressBook addressBook);

}
