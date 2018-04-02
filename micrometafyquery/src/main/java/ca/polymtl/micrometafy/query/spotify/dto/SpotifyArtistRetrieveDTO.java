package ca.polymtl.micrometafy.query.spotify.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to retrieve Artist data from Spotify Web Api.
 */
@XmlRootElement
public class SpotifyArtistRetrieveDTO {

    @XmlElement(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SpotifyArtistRetrieveDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
