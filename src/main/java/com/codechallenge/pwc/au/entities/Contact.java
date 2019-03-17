package com.codechallenge.pwc.au.entities;

/**
 * A POJO object representing contact details.
 *
 * @author Joshua Nwankwo
 * @version 1.0
 * @since March 2019
 */

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


    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
