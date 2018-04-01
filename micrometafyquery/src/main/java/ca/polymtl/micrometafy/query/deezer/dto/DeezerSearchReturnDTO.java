package ca.polymtl.micrometafy.query.deezer.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author thcoud, wmouchere
 * This class is a Data Transfer Object used to retrieve search data from Deezer Web Api.
 */
@XmlRootElement
public class DeezerSearchReturnDTO {

    @XmlElement(name = "data")
    private List<DeezerTrackReturnDTO> items;

    public List<DeezerTrackReturnDTO> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "DeezerSearchReturnDTO{" +
                "items=" + items +
                '}';
    }
}
