package co.igorski.extension;


import co.igorski.model.CrashResponse;
import co.igorski.services.SamebugProxy;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * JUnit 5 extension for Samebug.
 * <p>
 * Every time a test fails and throws an exception the stacktrace is sent to Samebug for analysis. Samebug responds
 * with an id of the response and a url is logged that the user can visit to look for a solution for the problem
 * that caused the exception.
 *
 * @since 1.0.0
 */
public class SamebugExtension implements AfterEachCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(SamebugExtension.class);
    private final SamebugProxy samebugProxy;

    public SamebugExtension() {
        this(new SamebugProxy());
    }

    private SamebugExtension(SamebugProxy samebugProxy) {
        this.samebugProxy = samebugProxy;
    }

    public void afterEach(ExtensionContext extensionContext) {
        Optional<Throwable> throwable = extensionContext.getExecutionException();

        if (throwable.isPresent()) {
            CrashResponse crashResponse = samebugProxy.getSamebugRequest(throwable.get());

            if (crashResponse != null && crashResponse.getData() != null) {
                LOGGER.info("Please visit https://nightly.samebug.com/searches/" + crashResponse.getData().getId() + " for more info.");
            }
        }
    }
}
