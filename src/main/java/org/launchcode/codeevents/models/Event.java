package org.launchcode.codeevents.models;

 //TODO something to do with these import statements. I swear they WERE working at some point. No clue why not now.
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.util.Objects;


public class Event {

    private int id;
    private static int nextId = 1; //TODO note to self: this count still increments even if creating an object throws an error.

    @NotBlank(message = "email cannot be blank")
    //can't get this to throw an error when editing an event. It will accept an invalid Email.
    @Email(message = "Invalid email address.") //this only checks to see if it's X@Y, doesn't need a .com or anything to pass validation.
    private String contactEmail;

    @NotBlank(message = "Must have a name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;


    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotBlank(message = "Please specify a location.")
    private String location;

    private boolean registrationRequired;

    @Min(value = 1, message = "Number of attendees must be greater than 0")
    private int attendees;


    public Event(String contactEmail, String name, String description, String location, boolean registrationRequired, int attendees) {
        this();
        this.contactEmail = contactEmail;
        this.name = name;
        this.description = description;
        this.location = location;
        this.registrationRequired = registrationRequired;
        this.attendees = attendees;
    }

    public Event()  {
        this.id = nextId;
        nextId++;
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

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "name='" + name + '\'' +", description='" + description + '\'';
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
