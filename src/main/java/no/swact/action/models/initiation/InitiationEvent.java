package no.swact.action.models.initiation;

import no.swact.action.models.Event;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("initiation")
public class InitiationEvent extends Event {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InitiationSchedule> schedules = new ArrayList<>();

    public List<InitiationSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<InitiationSchedule> schedules) {
        this.schedules = schedules;
    }
}
