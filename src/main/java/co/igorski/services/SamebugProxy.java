package co.igorski.services;

import co.igorski.clients.SamebugClient;
import co.igorski.configuration.PropertiesStore;
import co.igorski.model.CrashResponse;
import co.igorski.model.SamebugRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;

/**
 * A proxy or a facade to Samebug.
 *
 * @since 1.0.0
 */
public class SamebugProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(SamebugProxy.class);
    private final SamebugClient samebugClient;
    private final ExceptionRequestFactory samebugRequestFactory;

    public SamebugProxy() {
        this(new SamebugRequestFactory(), new SamebugClientFactory(new PropertiesStore()));
    }

    SamebugProxy(ExceptionRequestFactory samebugRequestFactory, SamebugClientFactory samebugClientFactory) {
        this.samebugRequestFactory = samebugRequestFactory;
        this.samebugClient = samebugClientFactory.getInstance();
    }

    /**
     * Has a single method that takes a {@link Throwable} and based on it sends a request to Samebug to look for an
     * answer.
     *
     * @param throwable
     *            the throwable to look a solution for
     * @return a crash response containing the id of the Samebug answer
     */
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
