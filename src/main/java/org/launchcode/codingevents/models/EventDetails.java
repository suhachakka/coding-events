package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class EventDetails extends AbstractEntity{

    @Size(max = 500, message = "Description too long!")
    private String description;

    @Email(message = "Invalid email. Try again.")
    private String contactEmail;
//this app won't need inverse relationship because they have the reference key to match the eventdetails in event
//    @OneToOne(mappedBy = "eventDetails")
//    private Event event;

    public EventDetails(){}

    public EventDetails(@Size(max = 500, message = "Description too long!") String description,
                        @Email(message = "Invalid email. Try again.") String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;
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

//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
