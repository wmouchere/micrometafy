package ca.polymtl.micrometafy.query.spotify;

import ca.polymtl.micrometafy.query.IStreamerApi;
import ca.polymtl.micrometafy.query.TrackDTO;
import ca.polymtl.micrometafy.query.authentication.SpotifyAuthenticator;
import ca.polymtl.micrometafy.query.spotify.dto.SpotifySearchRetrieveDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class is a Singleton used to make HTTP queries to the Spotify Web API.
 * @author wmouchere
 */
public class SpotifyApi implements IStreamerApi {

    private static SpotifyApi instance = null;
    private static final Logger LOGGER = Logger.getLogger(SpotifyApi.class.getName());

    private SpotifyAuthenticator authenticator;
    private Client client;

    private SpotifyApi() {
        client = ClientBuilder.newClient();
        authenticator = SpotifyAuthenticator.getInstance();
    }

    /**
     * Get the SpotifyApi service instance. Instance is lazy initialized.
     * @return The SpotifyApi service instance
     */
    public static SpotifyApi getInstance() {
        if(instance == null)
            instance = new SpotifyApi();
        return instance;
    }

    public List<TrackDTO> searchTrack(String queryString) {
        WebTarget resource = client.target("https://api.spotify.com/v1/search")
                .queryParam("q", queryString)
                .queryParam("type", "track")
                .queryParam("limit", "10");
        SpotifySearchRetrieveDTO response = resource.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + authenticator.getToken())
                .get(SpotifySearchRetrieveDTO.class);
        LOGGER.log(Level.INFO, () -> "Queried \"" + queryString + "\" on Spotify API, response was " + response);
        return response.getTracksReturnDTO().getItems().stream()
                .map(item -> new TrackDTO(item.getTrackName(),
                        item.getArtists().get(0).getName(),
                        item.getTrackURL(),
                        item.getTrackDuration(),
                        "Spotify"))
                .collect(Collectors.toList());
    }
}
