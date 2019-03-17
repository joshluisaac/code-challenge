package com.codechallenge.pwc.au.services;

import java.util.Map;
import java.util.SortedMap;

public interface IAddressBookUnionService {

    public SortedMap<String,String> union(Map<String, String> addressBook1, Map<String, String> addressBook2);
}
