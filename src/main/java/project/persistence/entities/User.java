package project.persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by geelo on 23-Oct-16.
 */

@Entity
@Table(name = "users") // If you want to specify a table name, you can do so here
public class User {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // fbId cannot be int because it's 17 digits long and is crazy high
    private String fbId;
    private String name;
    private String email;
    private int age;
    private String gender;
    private ArrayList<Rating> ratings;
    private ArrayList<Event> createdEvents;
    private ArrayList<Event> attendedEvents;


    // Notice the empty constructor, because we need to be able to create an empty User to add
    // to our model so we can use it with our form 
    public User() {
    }



    public User(String name, String fbId, String email, int age, String gender, ArrayList<Rating> ratings,
                ArrayList<Event> createdEvents, ArrayList<Event> attendedEvents) {
        this.name = name;
        this.email = email;
        this.fbId = fbId;
        this.age = age;
        this.gender = gender;
        this.ratings = ratings;
        this.createdEvents = createdEvents;
        this.attendedEvents = attendedEvents;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getfbId() { return fbId; }

    public void setfbId(String id) { this.fbId = id; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public ArrayList<Rating> getRatings() { return ratings; }

    public void setRatings(ArrayList<Rating> ratings) { this.ratings = ratings; }

    public ArrayList<Event> getCreatedEvents() { return createdEvents; }

    public void setCreatedEvents(ArrayList<Event> createdEvents) { this.createdEvents = createdEvents; }

    public ArrayList<Event> getAttendedEvents() { return attendedEvents; }

    public void setAttendedEvents(ArrayList<Event> attendedEvents) { this.attendedEvents = attendedEvents; }

}
