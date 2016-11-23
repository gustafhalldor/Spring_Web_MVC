package project.persistence.entities;

import javax.persistence.*;

import java.awt.*;
import java.sql.Time;
import java.sql.Timestamp;
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
    private ArrayList<Integer> attendees;
    private float lgt;
    private float lat;
    //private ArrayList<String> type
    private int creatorId;
    private Timestamp startDate;
    private Timestamp endDate;


    // Notice the empty constructor, because we need to be able to create an empty Event to add
    // to our model so we can use it with our form
    public Event() {

    }

    public Event(String name, String description, int ageMax, int ageMin, boolean genderRestriction, ArrayList<String> type,
                 Location location, ArrayList<User> attendees, int creatorId, float lat, float lgt, Timestamp startDate, Timestamp endDate) {
        this.name = name;
        this.description = description;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.genderRestriction = genderRestriction;
        this.lgt = lgt;
        this.lat = lat;
        this.startDate = startDate;
        this.endDate = endDate;
      //  this.location = location;
        this.attendees = new ArrayList<Integer>();
        this.creatorId = creatorId;
       // this.type = type;

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

    public void setAgeMin(int ageMin) { if(ageMin >= 18) this.ageMin = ageMin; }

    public int getAgeMax() { return ageMax; }

    public void setAgeMax(int ageMax) { if(ageMax >= 18) this.ageMax = ageMax; }

    public boolean getGenderRestriction() { return genderRestriction; }

    public void setGenderRestriction(boolean genderRestriction) { this.genderRestriction = genderRestriction; }

    public float getLgt(){return this.lgt;}

    public void setLgt(float lgt){this.lgt = lgt;}

    public float getLat(){return this.lat;}

    public void setLat(float lat){this.lat = lat;}


    public void setAttendee(Integer userID) {
        if(this.attendees == null){
            this.attendees = new ArrayList<Integer>();
        }
        if(this.attendees.contains(userID)) return;
        else {
            this.attendees.add(userID);
        }
    }

    public void setAttendees(ArrayList<Integer> attendees) { this.attendees = attendees; }

    public ArrayList<Integer> getAttendees() {
        if(this.attendees == null){
            this.attendees = new ArrayList<Integer>();
        }
        return this.attendees;
    }


    public Timestamp getStartDate() { return startDate; }

    public void setStartDate(Timestamp date) { this.startDate = date; }

    public Timestamp getEndDate() { return endDate; }

    public void setEndDate(Timestamp date) { this.endDate = date; }


    public int getCreatorId() { return creatorId; }

    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }
 /*
    public Location getLocation() { return location; }

    public void setLocation(Location location) { this.location = location; }

    public int getCreatorId() { return creatorId; }

    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }

    public ArrayList<User> getAttendees() { return attendees; }



*/
}
