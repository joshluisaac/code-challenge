package com.codechallenge.pwc.au.entities;

public class AddressBook {

    final FullName name;
    final String phoneNumber;

    public AddressBook(FullName name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


    public FullName getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
