package no.swact.action.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Entity
public class UploadedImage {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String s3Bucket;
    @Transient
    private InputStream inputStream;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @JsonGetter
    public URL getUrl() throws MalformedURLException {
        return new URL("https://s3.amazonaws.com/" + s3Bucket + "/" + getActualFileName());
    }

    public String getActualFileName() {
        return id + "/" + name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setBucket(final String bucket) {
        this.s3Bucket = bucket;
    }
}