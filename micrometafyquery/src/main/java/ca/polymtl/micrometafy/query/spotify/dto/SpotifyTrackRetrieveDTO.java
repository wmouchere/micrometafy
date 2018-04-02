package ca.polymtl.micrometafy.query.spotify.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to retrieve Track data from Spotify Web Api.
 */
@XmlRootElement
public class SpotifyTrackRetrieveDTO {

    @XmlElement(name = "name")
    private String trackName;

    @XmlElement(name = "artists")
    private List<SpotifyArtistRetrieveDTO> artists;

    @XmlElement(name = "duration_ms")
    private long trackDuration;

    @XmlElement(name = "preview_url")
    private String trackURL;

    public String getTrackName() {
        return trackName;
    }

    public List<SpotifyArtistRetrieveDTO> getArtists() {
        return artists;
    }

    public long getTrackDuration() {
        return trackDuration;
    }

    public String getTrackURL() {
        return trackURL;
    }

    @Override
    public String toString() {
        return "SpotifyTrackRetrieveDTO{" +
                "trackName='" + trackName + '\'' +
                ", artists=" + artists +
                ", duration=" + trackDuration +
                ", trackURL='" + trackURL + '\'' +
                '}';
    }
}
