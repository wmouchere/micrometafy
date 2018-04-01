package ca.polymtl.micrometafy.playlist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxence, wmouchere
 * This class represents the list of tracks created by the user.
 * It is used by the music player to play each track after an other
 */
@Entity(name = "PLAYLIST")
@NamedQueries({
        @NamedQuery(name = "Playlist.findAll", query = "SELECT p FROM PLAYLIST p"),
        @NamedQuery(name = "Playlist.findById", query = "SELECT p FROM PLAYLIST p WHERE p.id=:id")
})
public class Playlist extends PlaylistElement {

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JoinTable(
            name = "PLAYLIST_TRACKS",
            joinColumns = { @JoinColumn(name = "PLAYLIST_ID", referencedColumnName = "ID")},
            inverseJoinColumns = { @JoinColumn(name = "TRACK_ID", referencedColumnName = "ID", unique = true)}
    )
    /**
     * We use a JoinTable in the SQL database because we want a simple OneToMany relation (composition), and no reverse references.
     */
    private List<Track> tracks;

    public Playlist(String name){
        this.name = name;
        this.tracks = new ArrayList<>();
    }

    public Playlist() {
        this.tracks = new ArrayList<>();
    }

    /**
     * Add a track to the playlist
     * @param t track to be added to the list
     */
    public void addTrack(Track t){
        this.tracks.add(t);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", tracks=" + tracks +
                ", id=" + id +
                '}';
    }
}
