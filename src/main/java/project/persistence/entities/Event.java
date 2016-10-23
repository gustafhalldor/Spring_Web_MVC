package project.persistence.entities;

import javax.persistence.*;
import project.persistence.entities.Location;
import project.persistence.entities.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * The class for the Postit Note itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "events") // If you want to specify a table name, you can do so here
public class Event {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private Location location;
    private String[] type;
    private int creatorId;
    private int ageMin;
    private int ageMax;
    private Date time;
    private boolean genderRestriction;
    private Image image;
    private ArrayList<User> attendees;

    // Notice the empty constructor, because we need to be able to create an empty PostitNote to add
    // to our model so we can use it with our form
    public Event() {
    }

    public Event(String name, String description, Location location, String[] type, int creatorId, int ageMin, int ageMax, Date time,
                      boolean genderRestriction, Image image, ArrayList<User> attendees) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.creatorId = creatorId;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.time = time;
        this.genderRestriction = genderRestriction;
        this.image = image;
        this.attendees = attendees;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
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

    public String[] getType() { return type; }

    public void setType(String[] type) { this.type = type; }

    public int getCreatorId() { return creatorId; }

    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }

    public int getAgeMin() { return ageMin; }

    public void setAgeMin(int ageMin) { this.ageMin = ageMin; }

    public int getAgeMax() { return ageMax; }

    public void setAgeMax(int ageMax) { this.ageMax = ageMax; }

    public Location getLocation() { return location; }

    public void setLocation(Location location) { this.location = location; }

    public Date getTime() { return time; }

    public void setTime(Date time) { this.time = time; }

    public boolean getGenderRestriction() { return genderRestriction; }

    public void setGenderRestriction(boolean genderRestriction) { this.genderRestriction = genderRestriction; }

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    public ArrayList<User> getAttendees() { return attendees; }

    public void setAttendees(User[] attendees) { this.attendees = attendees; }


    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Postit Note[name=%s, note=%s]",
                name,note);
    }
}
