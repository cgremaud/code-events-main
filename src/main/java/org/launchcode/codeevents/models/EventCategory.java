package org.launchcode.codeevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "eventCategory") //eventCategory is the field of Event that determines which EventCategory it is mapped to.
    private final List<Event> events = new ArrayList<>(); //why is this final? I guess cause lists are mutable? but then there's no real diff between final and not?

    public EventCategory(@Size(min = 3, message = "Must be at least 3 characters long") String name) {
        this.name = name;
    }

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }
}
