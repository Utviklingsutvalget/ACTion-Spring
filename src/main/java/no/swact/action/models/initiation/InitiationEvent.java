package no.swact.action.models.initiation;

import no.swact.action.models.Event;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class InitiationEvent extends Event {

    @ManyToMany(cascade = CascadeType.ALL)
    private List<InitiationSchedule> initiationSchedule;

    public List<InitiationSchedule> getInitiationSchedule() {
        return initiationSchedule;
    }

    public void setInitiationSchedule(List<InitiationSchedule> initiationSchedule) {
        this.initiationSchedule = initiationSchedule;
    }
}
