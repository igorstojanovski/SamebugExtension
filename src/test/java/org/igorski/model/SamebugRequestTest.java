package org.igorski.model;


import org.igorski.services.SamebugRequestFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SamebugRequestTest {

    @Test
    public void shouldCreateJsonRequestWithCauseAndCorrectMessages() {
        String exceptionMessage = "Exception message";
        String causeMessage = "Cause exception";
        Exception e = new SimpleException(exceptionMessage, new SimpleCause(causeMessage));
        SamebugRequestFactory factory = new SamebugRequestFactory();
        SamebugRequest request = factory.createInstance(e);

        assertThat(request.getCause()).isNotNull();
        assertThat(request.getMessage()).isEqualTo(exceptionMessage);
        assertThat(request.getCause().getMessage()).isEqualTo(causeMessage);

        assertThat(request.getFrames()).isNotEmpty();
        assertThat(request.getCause().getFrames()).isNotEmpty();
    }

    class SimpleException extends Exception {
        SimpleException(String exceptionMessage, SimpleCause cause) {
            super(exceptionMessage, cause);
        }
    }

    class SimpleCause extends Exception {
        SimpleCause(String exceptionMessage) {
            super(exceptionMessage);
        }
    }
}