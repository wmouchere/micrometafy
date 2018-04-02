package ca.polymtl.micrometafy.playlist.resource;

import ca.polymtl.micrometafy.playlist.Playlist;
import ca.polymtl.micrometafy.playlist.PlaylistDTO;
import ca.polymtl.micrometafy.playlist.Track;
import ca.polymtl.micrometafy.playlist.TrackDTO;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Path("/")
public class PlaylistResource {

    private static final String PLAYLIST_FIND_BY_ID = "Playlist.findById";
    private static final String PLAYLIST_FIND_ALL = "Playlist.findAll";

    private static final Logger LOGGER = Logger.getLogger(PlaylistResource.class.getName());

    private final EntityManagerFactory emf;
    private final EntityManager em;


    static {
        LOGGER.setLevel(Level.ALL);
    }

    public PlaylistResource() {
        super();

        emf = Persistence.createEntityManagerFactory("metafy");
        em = emf.createEntityManager();
    }

    /**
     * Cleans the connection with the database before system shutdown.
     */
    public void flush() {
        em.clear();
        em.close();
        emf.close();
    }

    @Override
    protected void finalize() throws Throwable {
        flush();
        super.finalize();
    }

    /**
     * This method is a HTTP POST request that creates and saves a new playlist in the system.
     * It expects a json only containing the field "name" of PlaylistDTO
     * @return The json response is a serialisation of the newly created playlist
     */
    @POST
    @Path("playlist/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newPlaylist(final PlaylistDTO playlistDto) {
        final EntityTransaction tr = em.getTransaction();
        try {
            Playlist playlist = new Playlist(playlistDto.getName());
            tr.begin();
            em.persist(playlist);
            tr.commit();
            LOGGER.log(Level.INFO, () -> "Added new playlist " + playlist);
            return Response.status(Response.Status.OK).entity(new PlaylistDTO(playlist)).build();
        } catch(final Exception ex) {
            if(tr.isActive()) {
                tr.rollback();
            }
            LOGGER.log(Level.SEVERE, "Crash on adding a new playlist " + playlistDto, ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    /**
     * This method is a HTTP GET request that returns the playlist that has the id passed as a query parameter.
     * @return The json response is a serialization of a PlaylistDTO
     */
    @GET
    @Path("playlist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistById(@PathParam("id") final int id) {
        try {
            final Playlist playlist = em.createNamedQuery(PLAYLIST_FIND_BY_ID, Playlist.class).setParameter("id", id).getSingleResult();
            LOGGER.log(Level.INFO, () -> "Get playlist with id " + id + ",got " + playlist);
            return Response.status(Response.Status.OK).entity(new PlaylistDTO(playlist)).build();
        } catch (final Exception ex) {
            LOGGER.log(Level.SEVERE, "Crash on finding playlist with id " + id, ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    /**
     * This method is a HTTP DELETE request that removes the playlist that has the id passed as a query parameter.
     * @return The json response is a serialization of a PlaylistDTO
     */
    @DELETE
    @Path("playlist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePlaylist(@PathParam("id") final int id) {
        final EntityTransaction tr = em.getTransaction();
        try {
            final Playlist playlist = em.createNamedQuery(PLAYLIST_FIND_BY_ID, Playlist.class).setParameter("id", id).getSingleResult();
            tr.begin();
            em.remove(playlist);
            tr.commit();
            LOGGER.log(Level.INFO, () -> "Sucessfully removed playlist with id " + id);
            return Response.status(Response.Status.OK).entity(new PlaylistDTO(playlist)).build();
        } catch (final Exception ex) {
            if(tr.isActive()) {
                tr.rollback();
            }
            LOGGER.log(Level.SEVERE, "Crash on removing playlist with id " + id, ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    /**
     * This method is a HTTP GET request that returns a list of all Playlists saved in the system.
     * @return The json response is a serialization of an array of PlaylistDTO
     */
    @GET
    @Path("playlists/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists() {
        try {
            final List<Playlist> playlists = em.createNamedQuery(PLAYLIST_FIND_ALL, Playlist.class).getResultList();
            LOGGER.log(Level.INFO, () -> "Get all playlists " + playlists);
            return Response.status(Response.Status.OK).entity(playlists.stream()
                    .map(PlaylistDTO::new)
                    .toArray(PlaylistDTO[]::new))
                    .build();
        } catch (final Exception ex) {
            LOGGER.log(Level.SEVERE, "Crash on finding all playlists", ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

    /**
     * This method is a HTTP PUT request that adds a Track to the playlist of the given id.
     * @return The json response is the updated playlist.
     */
    @PUT
    @Path("playlist/{id}/add/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") final int id, final TrackDTO trackDTO) {
        final EntityTransaction tr = em.getTransaction();
        try {
            final Playlist playlist = em.createNamedQuery(PLAYLIST_FIND_BY_ID, Playlist.class).setParameter("id", id).getSingleResult();
            Track track = new Track(trackDTO.getName(), trackDTO.getAuthor(), trackDTO.getUrl(), trackDTO.getDuration(), trackDTO.getOrigin());
            tr.begin();
            em.persist(track);
            playlist.addTrack(track);
            tr.commit();
            LOGGER.log(Level.INFO, () -> "Added track " + track + " to playlist " + playlist);
            return Response.status(Response.Status.OK).entity(new PlaylistDTO(playlist)).build();
        } catch (final Exception ex) {
            if(tr.isActive()) {
                tr.rollback();
            }
            LOGGER.log(Level.SEVERE, "Crash on adding track " + trackDTO + " to playlist with id " + id, ex);
            throw new WebApplicationException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build());
        }
    }

}
