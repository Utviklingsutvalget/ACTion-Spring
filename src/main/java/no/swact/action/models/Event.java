package no.swact.action.models;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class Event {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private ZonedDateTime dateTime;
    private String place;
    @Column(length = 350)
    private String description;
    @ManyToMany
    private List<User> attending = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(final String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<User> getAttending() {
        return attending;
    }

    public void setAttending(final List<User> attending) {
        this.attending = attending;
    }
}
