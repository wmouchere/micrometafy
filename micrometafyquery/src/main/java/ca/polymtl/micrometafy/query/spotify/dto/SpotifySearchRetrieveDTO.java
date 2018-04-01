package ca.polymtl.micrometafy.query.spotify.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to retrieve search query data from Spotify Web Api.
 */
@XmlRootElement
public class SpotifySearchRetrieveDTO {

    @XmlElement(name = "tracks")
    private SpotifyTracksRetrieveDTO tracksReturnDTO;

    public SpotifyTracksRetrieveDTO getTracksReturnDTO() {
        return tracksReturnDTO;
    }

    @Override
    public String toString() {
        return "SpotifySearchRetrieveDTO{" +
                "tracksReturnDTO=" + tracksReturnDTO +
                '}';
    }
}
