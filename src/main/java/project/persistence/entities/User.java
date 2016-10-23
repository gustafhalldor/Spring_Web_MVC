package project.persistence.entities;

import javax.persistence.*;

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
    private String name;
    private int age;
    private String gender;
    private List<Rating> ratings;
    private List<Event> createdEvents;
    private List<Event> attendedEvents;


    // Notice the empty constructor, because we need to be able to create an empty User to add
    // to our model so we can use it with our form
    public User() {
    }

    public User(int id, String name, int age, String gender, List<Rating> ratings,
                      List<Event> createdEvents, List<Event> attendedEvents) {
        this.id = id;
        this.name = name;
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

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public List<Rating> getRatings() { return ratings; }

    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

    public List<Event> getCreatedEvents() { return createdEvents; }

    public void setCreatedEvents(List<Event> createdEvents) { this.createdEvents = createdEvents; }

    public List<Event> getAttendedEvents() { return attendedEvents; }

    public void setAttendedEvents(List<Event> attendedEvents) { this.attendedEvents = attendedEvents; }


    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Postit Note[name=%s, note=%s]",
                name,note);
    }
}
