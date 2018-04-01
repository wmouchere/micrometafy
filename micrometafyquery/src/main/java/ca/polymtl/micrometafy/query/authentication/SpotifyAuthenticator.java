package ca.polymtl.micrometafy.query.authentication;

import ca.polymtl.micrometafy.query.authentication.dto.SpotifyAuthenticationReturnDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Singleton is used to authenticate to the Spotify Web API and retrieve the token.
 * @author wmouchere
 */
public class SpotifyAuthenticator implements IAuthenticator {

    private static SpotifyAuthenticator instance = null;
    private static final Logger LOGGER = Logger.getLogger(SpotifyAuthenticator.class.getName());

    private static String apiKey;
    //retrieve API key from loader
    static {
        try {
            apiKey = ApiKeyLoader.getInstance().getApiKey("Spotify");
        } catch (ApiKeyNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private String token;
    private long tokenExpirationDate;

    private SpotifyAuthenticator() {
        refreshToken();
    }

    /**
     * Get the SpotifyAuthenticator service instance. Instance is lazy initialized.
     * @return The SpotifyAuthenticator service instance
     */
    public static SpotifyAuthenticator getInstance() {
        if(instance == null)
            instance = new SpotifyAuthenticator();
        return instance;
    }

    public String getToken() {
        if(System.currentTimeMillis() > tokenExpirationDate)
            refreshToken();
        return token;
    }

    public void refreshToken() {
        Client client = ClientBuilder.newClient();

        WebTarget resource = client.target("https://accounts.spotify.com/api/token");
        Form form = new Form();
        form.param("grant_type", "client_credentials");

        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        request.header("Authorization", "Basic " + apiKey);
        SpotifyAuthenticationReturnDTO response = request.post(Entity.form(form), SpotifyAuthenticationReturnDTO.class);

        LOGGER.log(Level.INFO, "Refreshed token for Spotify API " + response.getAccessToken());

        token = response.getAccessToken();
        tokenExpirationDate = System.currentTimeMillis() + response.getDuration();
    }
}
