package org.igorski.extension;


import org.igorski.model.CrashResponse;
import org.igorski.services.SamebugProxy;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class SamebugExtension implements AfterEachCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(SamebugExtension.class);
    private final SamebugProxy samebugProxy;

    public SamebugExtension() {
        this(new SamebugProxy());
    }

    protected SamebugExtension(SamebugProxy samebugProxy) {
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
