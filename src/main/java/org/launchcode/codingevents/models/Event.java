package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
/** Our JPA needs to know what Java class is going to be converted to a table in the MySQL database.
A Java class that models a persistent data store is called a persistent class or entity class.
 entity classes determine the structure of a table in our relational database */
@Entity
public class Event extends AbstractEntity {
   /* @Id
    @GeneratedValue
   private  int id;*/
   //private static int nextId =1;
   @NotBlank(message = "Name is required.")
   @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @Min(50)
    private Integer Attendees;

    //private EventType type;

    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;
    //no-arg constructor
    /** The second one is protected and has no arguments and/or no return statement.
    While you must set up this second constructor,
    it will only be used by the JPA to create a new instance.*/
    public Event(){
        //this.id = idSequence.incrementAndGet();
//        this.id= nextId;
//         nextId++;
    }

    //The  second constructor creates an instance of the class.
    public Event(String name, String description,String contactEmail,Integer Attendees,
            EventCategory eventCategory /*EventType type*/) {

        this();
        this.name = name;
        this.description = description;
        this.contactEmail=contactEmail;
        this.Attendees=Attendees;
        //this.type=type;
        this.eventCategory=eventCategory;
    }



    /*public int getId() {
        return id;
    }*/

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

//    public EventType getType() {
//        return type;
//    }
//
//    public void setType(EventType type) {
//        this.type = type;
//    }


    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    @Override
    public String toString() {
        return name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Event event = (Event) o;
//        return id == event.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
