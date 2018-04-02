package ca.polymtl.micrometafy.query.authentication;

import org.glassfish.jersey.internal.util.Base64;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @author wmouchere
 *  This class is a service that loads the api keys used for spotify, deezer and jamendo (possibly others).
 *  Keys are made available through the method getApiKey().
 */
public final class ApiKeyLoader {

    private static final Logger LOGGER = Logger.getLogger(ApiKeyLoader.class.getName());

    private static final String CONFIG_FILE = "apikeys.yml";
    private static final ApiKeyLoader INSTANCE;

    private Map<String, String> keys;

    static {
        try {
            INSTANCE = new ApiKeyLoader();
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ApiKeyLoader() throws IOException {
        keys = new HashMap<>();
        try (InputStream is = new FileInputStream(CONFIG_FILE)){
            Yaml yaml = new Yaml();
            Map map = yaml.load(is);
            for(Object o : map.keySet()) {
                String apiId = (String) o;
                String key;
                Map inner = (Map) map.get(apiId);
                switch(apiId) {
                    case "Spotify":
                        key = Base64.encodeAsString(inner.get("user") + ":" + inner.get("key"));
                        break;
                    case "Jamendo":
                        key = (String) inner.get("key");
                        break;
                    case "Deezer":
                        key = (String) inner.get("token");
                        break;
                    default:
                        continue;
                }
                keys.put(apiId, key);
                LOGGER.log(Level.INFO, () -> "Loaded key for " + apiId + " : " + key);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File " + CONFIG_FILE + " not found. Cannot start without api keys.", e);
            throw e;
        }
    }

    public static ApiKeyLoader getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the apiKey corresponding to the apiName parameter.
     * @param apiName
     * @return a base64 encoded string usable for authentication.
     * @throws ApiKeyNotFoundException if the apiName does not match any loaded key name.
     */
    public String getApiKey(String apiName) throws ApiKeyNotFoundException {
        if(keys.containsKey(apiName)) {
            return keys.get(apiName);
        } else {
            LOGGER.log(Level.SEVERE, () -> "Api key not found for Api name " + apiName + ".");
            throw new ApiKeyNotFoundException();
        }
    }
}
