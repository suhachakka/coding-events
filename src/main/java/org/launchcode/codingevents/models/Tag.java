package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private final List<Event> events = new ArrayList<>();

    public Tag(@NotBlank(message = "Name is required")
               @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String tagName) {
        this.tagName = tagName;
    }

    public Tag() {
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Event> getEvents() {
        return events;
    }
    public String getDisplayName() {
        return "#" + tagName + " ";
    }
}
