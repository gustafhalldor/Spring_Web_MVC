package project.persistence.entities;

import javax.persistence.*;

/**
 * Created by geelo on 23-Oct-16.
 */

@Entity
@Table(name = "ratings") // If you want to specify a table name, you can do so here
public class Rating {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int hostRating;
    private int eventRating;
    private float avgRating;
    private String comment;
    private int userId;

    // Notice the empty constructor, because we need to be able to create an empty Rating to add
    // to our model so we can use it with our form
    public Rating() {
    }

    public Rating(int hostRating, int eventRating, float avgRating, String comment, int userId){
        this.hostRating = hostRating;
        this.eventRating = eventRating;
        this.avgRating = avgRating;
        this.comment = comment;
        this.userId = userId;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getHostRating() { return hostRating; }

    public void setHostRating(int hostRating) { this.hostRating = hostRating; }

    public int getEventRating() { return eventRating; }

    public void setEventRating(int eventRating) { this.eventRating = eventRating; }

    public float getAvgRating() { return avgRating; }

    public void setAvgRating(float avgRating) { this.avgRating = avgRating; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public int getUserID() { return userId; }

    public void setUserID(int user) { this.userId = userId; }


}
