package com.gojek.sendhil.mcm.util;

import com.gojek.sendhil.mcm.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class MockModelFabric {

    public static List<Contact> newListOfContacts(int numContacts) {
        List<Contact> contactList = new ArrayList<>(numContacts);
        for (int i = 0; i < numContacts; i++) {
            contactList.add(newContact("Repo " + i));
        }
        return contactList;
    }

    public static Contact newContact(String name) {
        Random random = new Random();
        Contact contact = new Contact();
        contact.setFirstName(name);
        return contact;
    }

}
