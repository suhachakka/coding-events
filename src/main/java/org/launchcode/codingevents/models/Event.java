package org.launchcode.codingevents.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
    //private String id = UUID.randomUUID().toString();
   private  int id;
//    private static AtomicInteger idSequence = new AtomicInteger();
   private static int nextId =0;
   @NotBlank(message = "Name is required.")
   @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @Min(1)
    private Integer Attendees;

    @Size(min = 3, max = 50, message = "location must be morethan 3 chars")
    private String location;

    @AssertTrue(message = "Mandatory to register")
    private boolean register;

     @FutureOrPresent(message = "Enter valid date")
     @DateTimeFormat(pattern="yyyy-MM-dd")
     private Date eventDate;
    //no-arg constructor
    public Event(){
        //this.id = idSequence.incrementAndGet();
        this.id= nextId;
         nextId++;
    }


    public Event(String name, String description,String contactEmail,Integer Attendees,
                 String location,boolean register,Date eventDate) {

        this();

        this.name = name;
        this.description = description;
        this.contactEmail=contactEmail;
        this.Attendees=Attendees;
        this.location=location;
        this.register=register;
        this.eventDate=eventDate;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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
