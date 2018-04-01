package ca.polymtl.micrometafy.playlist;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to send Playlist data.
 */
@XmlRootElement
public class PlaylistDTO {

    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "tracks")
    private List<TrackDTO> tracks;

    // Empty default constructor necessary for JSON marshalling
    public PlaylistDTO() {
        this.tracks = new ArrayList<>();
    }

    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        this.tracks = playlist.getTracks().stream()
                .map(TrackDTO::new)
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PlaylistDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tracks=" + tracks +
                '}';
    }
}
