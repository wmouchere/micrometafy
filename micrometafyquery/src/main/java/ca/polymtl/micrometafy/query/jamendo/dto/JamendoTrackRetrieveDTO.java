package ca.polymtl.micrometafy.query.jamendo.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to retrieve track data from Jamendo Web Api.
 */
@XmlRootElement
public class JamendoTrackRetrieveDTO {

    @XmlElement(name = "name")
    private String trackName;

    @XmlElement(name = "artist_name")
    private String artistName;

    @XmlElement(name = "duration")
    private long trackDuration;

    @XmlElement(name = "audio")
    private String trackURL;

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public long getTrackDuration() {
        return trackDuration;
    }

    public String getTrackURL() {
        return trackURL;
    }

    @Override
    public String toString() {
        return "JamendoTrackRetrieveDTO{" +
                "trackName='" + trackName + '\'' +
                ", artist=" + artistName +
                ", duration=" + trackDuration +
                ", trackURL='" + trackURL + '\'' +
                '}';
    }
}
