package com.codechallenge.pwc.au.entities;

public class Contact {

    String name;
    String phoneNumber;

    public Contact(){}

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
