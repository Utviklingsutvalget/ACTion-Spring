package no.swact.action.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class InitiationEvent {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private LocalDateTime dateTime;
    private String place;
    @Column (length = 300)
    private String description;
    @ManyToOne
    private InitiationSchedule initiationSchedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InitiationSchedule getInitiationSchedule() {
        return initiationSchedule;
    }

    public void setInitiationSchedule(InitiationSchedule initiationSchedule) {
        this.initiationSchedule = initiationSchedule;
    }
}
