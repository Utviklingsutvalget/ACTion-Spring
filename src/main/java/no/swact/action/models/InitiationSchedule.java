package no.swact.action.models;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

@Entity
public class InitiationSchedule {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Location location;
    private Year year;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<InitiationEvent> initiationEvents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<InitiationEvent> getInitiationEvents() {
        return initiationEvents;
    }

    public void setInitiationEvents(List<InitiationEvent> initiationEvents) {
        this.initiationEvents = initiationEvents;
    }
}
