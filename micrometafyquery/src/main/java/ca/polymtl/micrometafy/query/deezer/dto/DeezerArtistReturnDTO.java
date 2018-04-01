package ca.polymtl.micrometafy.query.deezer.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
* @author wmouchere
* This class is a Data Transfer Object used to retrieve Artist data from Deezer Web Api.
*/
@XmlRootElement
public class DeezerArtistReturnDTO {

    @XmlElement(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DeezerArtistReturnDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
