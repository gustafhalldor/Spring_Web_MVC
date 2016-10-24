package project.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The class for the Postit Note itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */

@Entity
@Table(name = "locations") // If you want to specify a table name, you can do so here
public class Location implements Serializable{

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float latitude;
    private float longitude;


    // Notice the empty constructor, because we need to be able to create an empty PostitNote to add
    // to our model so we can use it with our form
    public Location() {
    }

    public Location(float latitude, float longitude ) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLatitude() { return latitude; }

    public void setLatitude(float latitude) { this.latitude = latitude; }

    public float getLongitude() { return longitude; }

    public void setLongitude(float longitude) { this.longitude = longitude; }

}
