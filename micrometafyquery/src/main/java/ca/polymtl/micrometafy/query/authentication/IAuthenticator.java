package ca.polymtl.micrometafy.query.authentication;

/**
 * @author wmouchere
 * Interface representing
 */
public interface IAuthenticator {

    /**
     * Returns an authorization token for the API. If the current token is known to be outdated, will refresh it.
     * @return A token that is at least new enough to work
     */
    String getToken();

    /**
     * Forcefully refresh the authorization token for the API.
     */
    void refreshToken();

}
