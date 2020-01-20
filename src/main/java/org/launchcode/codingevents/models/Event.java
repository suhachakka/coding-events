package org.launchcode.codingevents.models;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
    //private String id = UUID.randomUUID().toString();
   private  int id;
    private static AtomicInteger idSequence = new AtomicInteger();
   //private static int nextId =1;
    private String name;
    private String description;


    public Event(String name, String description) {
//        this.id=nextId;
//        nextId++;
        this.id = idSequence.incrementAndGet();
        this.name = name;
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
