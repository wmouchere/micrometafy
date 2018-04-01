package ca.polymtl.micrometafy.query.resource;

import ca.polymtl.micrometafy.query.IStreamerApi;
import ca.polymtl.micrometafy.query.TrackDTO;
import ca.polymtl.micrometafy.query.deezer.DeezerApi;
import ca.polymtl.micrometafy.query.jamendo.JamendoApi;
import ca.polymtl.micrometafy.query.spotify.SpotifyApi;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Path("/")
public class QueryResource {

    private static final Logger LOGGER = Logger.getLogger(QueryResource.class.getName());
    private List<IStreamerApi> apis;

    static {
        LOGGER.setLevel(Level.ALL);
    }

    public QueryResource() {
        super();
        apis = new ArrayList<>();
        apis.add(SpotifyApi.getInstance());
        apis.add(DeezerApi.getInstance());
        apis.add(JamendoApi.getInstance());
    }

    /**
     * This Method is a HTTP GET request that returns a list of Tracks retrieved from all the services from the apis list.
     * @return The json response is a serialisation of an array of TrackDTO
     */
    @GET
    @Path("search/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("query") final String query) {

        return Response.status(Response.Status.OK)
                .entity(apis.stream()
                        .map(api -> api.searchTrack(query))
                        .flatMap(List::stream)
                        .toArray(TrackDTO[]::new))
                .build();
    }

}
