package ca.polymtl.micrometafy.query;

import java.util.List;

/**
 * @author wmouchere
 * Interface representing the possible actions on a music streaming service API.
 */
public interface IStreamerApi {

    /**
     * Search for a track on the streaming service API. This method runs an HTTP query.
     * @param queryString A String used to perform the search
     * @return A List of Music holding the results of the query
     */
    List<TrackDTO> searchTrack(String queryString);

}
