package org.launchcode.codeevents.models;

 //TODO something to do with these import statements. I swear they WERE working at some point. No clue why not now.
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.util.Objects;


public class Event {

    private int id;
    private static int nextId = 1; //TODO note to self: this count still increments even if creating an object throws an error.

    @NotBlank
    //can't get this to throw an error when editing an event. It will accept an invalid Email.
    @Email(message = "Invalid email address.") //this only checks to see if it's X@Y, doesn't need a .com or anything to pass validation.
    private String contactEmail;

    @NotBlank
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;



    public Event(String name, String description, String contactEmail) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;

    }

    public Event()  {
        this.id = nextId;
        nextId++
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
