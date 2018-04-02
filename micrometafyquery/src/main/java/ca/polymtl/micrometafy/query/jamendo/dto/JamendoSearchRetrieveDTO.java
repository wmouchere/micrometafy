package ca.polymtl.micrometafy.query.jamendo.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author wmouchere
 * This class is a Data Transfer Object used to retrieve search query data from Jamendo Web Api.
 */
@XmlRootElement
public class JamendoSearchRetrieveDTO {

    @XmlElement(name = "results")
    private List<JamendoTrackRetrieveDTO> tracksReturnDTO;

    public List<JamendoTrackRetrieveDTO> getTracksReturnDTO() {
        return tracksReturnDTO;
    }

    @Override
    public String toString() {
        return "JamendoSearchRetrieveDTO{" +
                "trackReturnDTO=" + tracksReturnDTO +
                '}';
    }
}