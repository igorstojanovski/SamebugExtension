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

        assertThat(request.cause).isNotNull();
        assertThat(request.message).isEqualTo(exceptionMessage);
        assertThat(request.cause.message).isEqualTo(causeMessage);

        assertThat(request.frames).isNotEmpty();
        assertThat(request.cause.frames).isNotEmpty();
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