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
  //  private ArrayList<User> attendees;
    private float lgt;
    private float lat;
    //private ArrayList<String> type
  //  private int creatorId;
  //  private Location location;
  //  private Date time;


    // Notice the empty constructor, because we need to be able to create an empty Event to add
    // to our model so we can use it with our form
    public Event() {
    }

    public Event(String name, String description, int ageMax, int ageMin, boolean genderRestriction, ArrayList<String> type,
                 Location location, ArrayList<User> attendees, int creatorId, float lat, float lgt) {
        this.name = name;
        this.description = description;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.genderRestriction = genderRestriction;
        this.lgt = lgt;
        this.lat = lat;
      //  this.location = location;
      //  this.attendees = attendees;
      //  this.creatorId = creatorId;
       // this.type = type;
      //  this.time = time;

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

   // public ArrayList<String>  getType() { return type; }

   // public void setType(ArrayList<String>  type) { this.type = type; }

    public int getAgeMin() { return ageMin; }

    public void setAgeMin(int ageMin) { this.ageMin = ageMin; }

    public int getAgeMax() { return ageMax; }

    public void setAgeMax(int ageMax) { this.ageMax = ageMax; }

    public boolean getGenderRestriction() { return genderRestriction; }

    public void setGenderRestriction(boolean genderRestriction) { this.genderRestriction = genderRestriction; }

    public float getLgt(){return this.lgt;}

    public void setLgt(float lgt){this.lgt = lgt;}

    public float getLat(){return this.lat;}

    public void setLat(float lat){this.lat = lat;}
 /*
    public int getCreatorId() { return creatorId; }

    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }

    public Location getLocation() { return location; }

    public void setLocation(Location location) { this.location = location; }

    public int getCreatorId() { return creatorId; }

    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }

    public ArrayList<User> getAttendees() { return attendees; }

    public void setAttendees(ArrayList<User> attendees) { this.attendees = attendees; }
/*
    public Date getTime() { return time; }

    public void setTime(Date time) { this.time = time; }
*/
}
