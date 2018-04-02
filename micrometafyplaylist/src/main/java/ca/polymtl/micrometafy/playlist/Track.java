package ca.polymtl.micrometafy.playlist;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Maxence, wmouchere
 * This class represent the tracks researched by the user.
 * It is used to display the result of a search or the content of a playlist.
 */
@Entity(name = "TRACK")
public class Track extends PlaylistElement {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private long duration;
    @Column(nullable = false)
    private String origin;

    public Track(String name, String author, String url, long duration, String origin){
        this.name = name;
        this.author = author;
        this.url = url;
        this.duration = duration;
        this.origin = origin;
    }

    public Track() {
    }

    public String getName(){
        return this.name;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getUrl() {
        return url;
    }

    public long getDuration(){
        return this.duration;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", duration=" + duration +
                ", origin='" + origin + '\'' +
                ", id=" + id +
                '}';
    }
}

