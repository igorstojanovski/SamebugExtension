package org.igorski.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Store for all configuration values needed for the Samebug extension.
 * It reads values from a file names application.properties in the resource folder.
 *
 * @since 1.0.0
 */
public class PropertiesStore {

    private static final Logger LOGGER = LoggerFactory.getLogger("PropertiesStore");
    private static final String ENDPOINT_KEY = "samebug.endpoint";
    private static final String API_KEY_KEY = "samebug.api-key";
    private String endpoint = "https://nightly.samebug.com/rest";
    private String apiKey = "";

    public PropertiesStore() {
        this("application.properties");
    }

    PropertiesStore(String propertiesFileName) {
        Properties properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (is != null) {
                properties.load(is);
                if (properties.containsKey(ENDPOINT_KEY)) {
                    endpoint = properties.getProperty(ENDPOINT_KEY);
                }
                if (properties.containsKey(API_KEY_KEY)) {
                    apiKey = properties.getProperty(API_KEY_KEY);
                }
            }
        } catch (IOException e) {
            LOGGER.debug("Could not find property file.", e);
        }
    }

    /**
     * Returns the api key value.
     *
     * @return api key value
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Returns the endpoint. If the file is not present in the configuration file, the default is:
     * {@code https://nightly.samebug.com/rest}
     *
     * @return endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }
}
