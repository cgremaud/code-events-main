package org.launchcode.codeevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class EventDetails extends AbstractEntity {

    @OneToOne(mappedBy = "eventDetails")
    private Event event;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid email address.") //this only checks to see if it's X@Y, doesn't need a .com or anything to pass validation.
    private String contactEmail;

    @Size(max = 500, message = "Description must be fewer than 500 characters")
    private String description;

    @NotBlank(message = "Please specify a location.")
    private String location;

    private boolean registrationRequired;

    @Min(value = 1, message = "Number of attendees must be greater than 0")
    private int attendees;

    public EventDetails(String contactEmail, String description, String location, boolean registrationRequired, int attendees) {
        this.contactEmail = contactEmail;
        this.description = description;
        this.location = location;
        this.registrationRequired = registrationRequired;
        this.attendees = attendees;
    }

    public EventDetails() {}

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public void setRegistrationRequired(boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }
}
