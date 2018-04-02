package ca.polymtl.micrometafy.query.spotify.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to retrieve list of Track data from Spotify Web Api.
 */
@XmlRootElement
public class SpotifyTracksRetrieveDTO {

    @XmlElement(name = "items")
    private List<SpotifyTrackRetrieveDTO> items;

    public List<SpotifyTrackRetrieveDTO> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SpotifyTracksRetrieveDTO{" +
                "items=" + items +
                '}';
    }
}
