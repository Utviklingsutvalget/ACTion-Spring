package no.swact.action.models.initiation;

import no.swact.action.models.Feed;
import no.swact.action.models.User;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("initiation")
public class InitiationFeed extends Feed<User> {

    @ManyToOne(cascade = CascadeType.ALL)
    private User postedBy;

    @Override
    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    @Override
    public User getPostedBy() {
        return this.postedBy;
    }

    @Override
    public String getPostedByString() {
        return "Fadderkomiteen - " + getPostedBy().getName();
    }
}
