package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
    //private String id = UUID.randomUUID().toString();
   private  int id;
   //private static AtomicInteger idSequence = new AtomicInteger();
    //private static final AtomicInteger idGenerator = new AtomicInteger();

    private static int nextId =1;
   @NotBlank(message = "Name is required.")
   @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @Min(1)
    @Max(100)
    private Integer Attendees;

    private EventType type;
    //no-arg constructor



    public Event(String name, String description,String contactEmail,Integer Attendees,EventType type) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail=contactEmail;
        this.Attendees=Attendees;
        this.type=type;

    }
    public Event(){
        this.id= nextId;
        nextId++;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getAttendees() {
        return Attendees;
    }

    public void setAttendees(Integer attendees) {
        Attendees = attendees;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
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
