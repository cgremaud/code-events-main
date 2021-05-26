package org.launchcode.codeevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Entity
public class Event extends AbstractEntity {

    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid email address.") //this only checks to see if it's X@Y, doesn't need a .com or anything to pass validation.
    private String contactEmail;

    @NotBlank(message = "Must have a name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description must be fewer than 500 characters")
    private String description;

    @NotBlank(message = "Please specify a location.")
    private String location;

    private boolean registrationRequired;

    @Min(value = 1, message = "Number of attendees must be greater than 0")
    private int attendees;

    @ManyToOne
    @NotNull(message = "Please select a category.")
    private EventCategory eventCategory;


    public Event(String contactEmail, String name, String description, String location, boolean registrationRequired, int attendees, EventCategory eventCategory) {
        this.contactEmail = contactEmail;
        this.name = name;
        this.description = description;
        this.location = location;
        this.registrationRequired = registrationRequired;
        this.attendees = attendees;
        this.eventCategory = eventCategory;
    }

    public Event()  {}

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public void setRegistrationRequired(boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }


    @Override
    public String toString() {
        return "name='" + name + '\'' +", description='" + description + '\'';
    }


}
