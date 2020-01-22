package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContactInfo {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String email;

    public ContactInfo(String n, String e) {
        this.name = n;
        this.email = e;
    }

    public ContactInfo() {}
}

