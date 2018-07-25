package co.igorski.services;

import co.igorski.clients.SamebugClient;
import co.igorski.configuration.PropertiesStore;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;

/**
 * Creates a {@link SamebugClient} instance for sending requests to Samebug.
 *
 * @since 1.0.0
 */
class SamebugClientFactory {

    private final PropertiesStore propertiesStore;
    private SamebugClient samebugClient;

    SamebugClientFactory(PropertiesStore propertiesStore) {
        this.propertiesStore = propertiesStore;
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
            requestContext.getHeaders().add("User-Agent", "JUnit-Extension/" + propertiesStore.getBuildVersion());
        });

        samebugClient = webTarget.proxy(SamebugClient.class);
    }


}
