package com.codechallenge.pwc.au.persistence;
import com.codechallenge.pwc.au.entities.Contact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    @Override
    public void writeToDataStore(final File file, final String content) throws IOException {
        try(BufferedWriter buff = new BufferedWriter(new FileWriter(file))){
            buff.write(content);
        }





    }

}
