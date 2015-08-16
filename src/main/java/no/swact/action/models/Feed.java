package no.swact.action.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Feed")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "eventType")
public abstract class Feed<T> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String snippet;

    @Column(length = 2000, nullable = false)
    private String text;

    private ZonedDateTime dateTime = ZonedDateTime.now();

    @OneToOne
    private UploadedImage image;

    public Feed() {
    }

    public abstract void setPostedBy(T postedBy);

    public abstract T getPostedBy();

    public abstract String getPostedByString();

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

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UploadedImage getImage() {
        return image;
    }

    public void setImage(UploadedImage image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
