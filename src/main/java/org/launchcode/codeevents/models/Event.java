package org.launchcode.codeevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Entity
public class Event extends AbstractEntity {


    @NotBlank(message = "Must have a name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @ManyToOne
    @NotNull(message = "Please select a category.")
    private EventCategory eventCategory;


    public Event(String name, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }

    public Event()  {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }


    @Override
    public String toString() {
        return "name='" + name + '\'' +", description='" + '\'';
    }


}
