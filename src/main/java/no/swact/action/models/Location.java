package no.swact.action.models;

import no.swact.action.models.initiation.InitiationSchedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "location")
    private List<InitiationSchedule> initiationSchedules = new ArrayList<>();

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
