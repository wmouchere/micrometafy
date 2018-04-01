package ca.polymtl.micrometafy.playlist;

import ca.polymtl.micrometafy.playlist.resource.PlaylistResource;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "api";
    public static final String HTTP_ADDRESS = "http://localhost:8081/micrometafy-playlist/" + BASE_URI;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    private static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig(PlaylistResource.class)
                .packages("ca.polymtl.micrometafy.playlist.resource")
                .register(CORSResponseFilter.class);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(HTTP_ADDRESS), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        System.in.read();
        server.shutdownNow();
    }
}
