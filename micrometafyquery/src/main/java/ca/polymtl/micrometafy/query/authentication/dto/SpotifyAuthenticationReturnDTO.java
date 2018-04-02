package ca.polymtl.micrometafy.query.authentication.dto;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to retrieve authentication token from Spotify Web Api.
 */
@XmlRootElement
public class SpotifyAuthenticationReturnDTO {

    @XmlElement(name = "access_token")
    private String accessToken;

    @XmlElement(name = "expires_in")
    private long duration;

    public String getAccessToken() {
        return accessToken;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "SpotifyAuthenticationReturnDTO{" +
                "accessToken='" + accessToken + '\'' +
                ", duration=" + duration +
                '}';
    }
}
