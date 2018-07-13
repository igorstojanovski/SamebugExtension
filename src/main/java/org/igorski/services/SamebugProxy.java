package org.igorski.services;

import org.igorski.clients.SamebugClient;
import org.igorski.configuration.PropertiesStore;
import org.igorski.model.CrashResponse;
import org.igorski.model.SamebugRequest;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.UriBuilder;

public class SamebugProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(SamebugProxy.class);
    private final SamebugClient samebugClient;
    private final ExceptionRequestFactory samebugRequestFactory;

    public SamebugProxy() {
        this(new PropertiesStore(), new SamebugRequestFactory());
    }

    protected SamebugProxy(PropertiesStore propertiesStore, ExceptionRequestFactory samebugRequestFactory) {
        this.samebugRequestFactory = samebugRequestFactory;

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget webTarget = client.target(UriBuilder.fromPath(propertiesStore.getEndpoint()));
        webTarget.request().header("X-Samebug-ApiKey", propertiesStore.getApiKey());
        samebugClient = webTarget.proxy(SamebugClient.class);
    }

    public CrashResponse getSamebugRequest(Throwable throwable) {
        SamebugRequest samebugRequest = samebugRequestFactory.createInstance(throwable);
        CrashResponse crashResponse = null;

        try {
            crashResponse = samebugClient.getCrashReport(samebugRequest);
        } catch (ProcessingException pe) {
            LOGGER.error("Error sending Samebug request.", pe);
        }

        return crashResponse;
    }
}
