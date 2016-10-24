package project.persistence.entities;

import javax.persistence.*;

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
    private int ageMin;
    private int ageMax;
    private boolean genderRestriction;
    private ArrayList<String> type;
  //  private int creatorId;
  //  private Location location;
  //  private Date time;

  //  private ArrayList<User> attendees;

    // Notice the empty constructor, because we need to be able to create an empty Event to add
    // to our model so we can use it with our form
    public Event() {
    }

    public Event(String name, String description, int ageMax, int ageMin, boolean genderRestriction, ArrayList<String> type) {
        this.name = name;
        this.description = description;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.genderRestriction = genderRestriction;
        this.type = type;
      //  this.time = time;
      //  this.attendees = attendees;
      //  this.location = location;
      //  this.creatorId = creatorId;
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

    public ArrayList<String>  getType() { return type; }

    public void setType(ArrayList<String>  type) { this.type = type; }

    public int getAgeMin() { return ageMin; }

    public void setAgeMin(int ageMin) { this.ageMin = ageMin; }

    public int getAgeMax() { return ageMax; }

    public void setAgeMax(int ageMax) { this.ageMax = ageMax; }

    public boolean getGenderRestriction() { return genderRestriction; }

    public void setGenderRestriction(boolean genderRestriction) { this.genderRestriction = genderRestriction; }
 /*
    public int getCreatorId() { return creatorId; }

    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }

    public Location getLocation() { return location; }

    public void setLocation(Location location) { this.location = location; }

    public Date getTime() { return time; }

    public void setTime(Date time) { this.time = time; }

    public ArrayList<User> getAttendees() { return attendees; }

    public void setAttendees(ArrayList<User> attendees) { this.attendees = attendees; }
*/
}
