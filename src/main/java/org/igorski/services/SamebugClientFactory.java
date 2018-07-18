package org.igorski.services;

import org.igorski.clients.SamebugClient;
import org.igorski.configuration.PropertiesStore;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.Properties;

/**
 * Creates a {@link SamebugClient} instance for sending requests to Samebug.
 *
 * @since 1.0.0
 */
class SamebugClientFactory {

    private final PropertiesStore propertiesStore;
    private final BuildProperties buildProperties;
    private SamebugClient samebugClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(SamebugClientFactory.class);

    SamebugClientFactory(PropertiesStore propertiesStore) {
        this.propertiesStore = propertiesStore;
        this.buildProperties = new BuildProperties();
    }

    /**
     * Returns a {@link SamebugClient} instance.
     *
     * @return the instance
     */
    SamebugClient getInstance() {
        if (samebugClient == null) {
            createSameBugClient();
        }
        return samebugClient;
    }

    private void createSameBugClient() {
        ResteasyClient client = new ResteasyClientBuilder().build();

        ResteasyWebTarget webTarget = client.target(UriBuilder.fromPath(propertiesStore.getEndpoint()));
        webTarget.register((ClientRequestFilter) requestContext -> {
            requestContext.getHeaders().add("X-Samebug-ApiKey", propertiesStore.getApiKey());
            requestContext.getHeaders().add("User-Agent", "JUnit-Extension/" + buildProperties.getBuildVersion());
        });

        samebugClient = webTarget.proxy(SamebugClient.class);
    }

    private class BuildProperties {
        private String buildVersion;
        static final String BUILD_PROPERTIES_FILE = "build.properties";
        static final String DEFAULT_BUILD_VERSION = "<undefined version>";
        static final String BUILD_VERSION_KEY = "version";

        BuildProperties() {
            var p = new Properties();
            try {
                p.load(getClass().getClassLoader().getResourceAsStream(BUILD_PROPERTIES_FILE));
            } catch (IOException e) {
                LOGGER.warn("Failed to read client properties file!", e);
            }
            buildVersion =  p.getProperty(BUILD_VERSION_KEY, DEFAULT_BUILD_VERSION);
            if(buildVersion.contains("${project.version}")) {
                buildVersion = DEFAULT_BUILD_VERSION;
            }
        }

        public String getBuildVersion() {
            return buildVersion;
        }
    }
}
