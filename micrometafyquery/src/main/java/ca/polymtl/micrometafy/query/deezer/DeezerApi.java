package ca.polymtl.micrometafy.query.deezer;

import ca.polymtl.micrometafy.query.IStreamerApi;
import ca.polymtl.micrometafy.query.TrackDTO;
import ca.polymtl.micrometafy.query.authentication.ApiKeyLoader;
import ca.polymtl.micrometafy.query.authentication.ApiKeyNotFoundException;
import ca.polymtl.micrometafy.query.deezer.dto.DeezerSearchReturnDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * Singleton to make HTTP requests to the Spotify API server
 * @author Theo Coulin, wmouchere
 */
public class DeezerApi implements IStreamerApi {

    private static DeezerApi instance = null;

    private static final Logger LOGGER = Logger.getLogger(DeezerApi.class.getName());

    private Client client;

    private static String apiKey;
    //retrieve API key from loader
    static {
        try {
            apiKey = ApiKeyLoader.getInstance().getApiKey("Deezer");
        } catch (ApiKeyNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public DeezerApi() {
        client = ClientBuilder.newClient();
    }

    /**
     * Get the DeezerApi service instance. Instance is lazy initialized.
     * @return The DeezerApi service instance
     */
    public static DeezerApi getInstance() {
        if(instance == null)
            instance = new DeezerApi();
        return instance;
    }

    public List<TrackDTO> searchTrack(String queryString) {
        WebTarget resource = client.target("https://api.deezer.com/search/track")
                .queryParam("limit", "10")
                .queryParam("q", queryString);
        DeezerSearchReturnDTO response = resource.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .get(DeezerSearchReturnDTO.class);

        LOGGER.log(Level.INFO, () -> "Queried \"" + queryString + "\" on Deezer API, response was " + response);

        return response.getItems().stream()
                .map(track -> new TrackDTO(track.getTitle(),
                        track.getArtist().getName(),
                        track.getTrackURL(),
                        track.getDuration()*1000,
                        "Deezer"))
                .collect(Collectors.toList());
    }

}
