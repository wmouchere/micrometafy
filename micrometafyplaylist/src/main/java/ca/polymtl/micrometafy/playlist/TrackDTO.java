package ca.polymtl.micrometafy.playlist;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author wmouchere
 * This class is a Data Transfer Object used to send Track data.
 */
@XmlRootElement
public class TrackDTO {

    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "author")
    private String author;
    @XmlElement(name = "url")
    private String url;
    @XmlElement(name = "duration")
    private long duration;
    @XmlElement(name = "origin")
    private String origin;

    // Empty default constructor necessary for JSON marshalling
    public TrackDTO(){    }

    public TrackDTO(int id, String name, String author, String url, long duration, String origin){
        this.id = id;
        this.name = name;
        this.author = author;
        this.url = url;
        this.duration = duration;
        this.origin = origin;
    }

    public TrackDTO(Track track){
        this.id = track.getId();
        this.name = track.getName();
        this.author = track.getAuthor();
        this.url = track.getUrl();
        this.duration = track.getDuration();
        this.origin = track.getOrigin();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public long getDuration() {
        return duration;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "TrackDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", duration=" + duration +
                ", origin='" + origin + '\'' +
                '}';
    }
}
