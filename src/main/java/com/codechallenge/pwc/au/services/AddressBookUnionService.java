package com.codechallenge.pwc.au.services;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * An implementation of {@link IAddressBookUnionService} that
 * takes two address books and resolves the uniqueness between them.
 */
public class AddressBookUnionService implements IAddressBookUnionService {


    /**
     * Takes two {@link Map} and resolves the difference between them resulting in a unique map.
     *
     * @param addressBook1 address book 1
     * @param addressBook2 address book 2
     * @return unique list of items
     */

    @Override
    public SortedMap<String, String> union(Map<String, String> addressBook1, Map<String, String> addressBook2) {
        SortedMap<String, String> unionMap = new TreeMap<>(String::compareToIgnoreCase);

        for (Map.Entry<String, String> entry : addressBook1.entrySet()) {
            if (!addressBook2.containsKey(entry.getKey())) unionMap.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String> entry : addressBook2.entrySet()) {
            if (!addressBook1.containsKey(entry.getKey())) unionMap.put(entry.getKey(), entry.getValue());
        }
        return unionMap;
    }


}
