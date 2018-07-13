package org.igorski.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesStore {

    private static final Logger LOGGER = LoggerFactory.getLogger("PropertiesStore");
    private static final String ENDPOINT_KEY = "samebug.endpoint";
    private static final String API_KEY_KEY = "samebug.api-key";
    private String endpoint = "https://nightly.samebug.com/rest";
    private String apiKey = "";

    public PropertiesStore() {
        this("application.properties");
    }

    protected PropertiesStore(String propertiesFileName) {
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

    public String getApiKey() {
        return apiKey;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
