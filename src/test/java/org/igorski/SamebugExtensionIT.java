package org.igorski;

import com.github.tomakehurst.wiremock.WireMockServer;
import extensions.WireMockExtension;
import org.igorski.model.CrashResponse;
import org.igorski.services.SamebugProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(WireMockExtension.class)
public class SamebugExtensionIT {

    @Test
    public void shouldSendCorrectContentToServer(WireMockServer server) {
        server.stubFor(
                post(urlEqualTo("/crashes"))
                        .withHeader("X-Samebug-ApiKey", containing("apiKey"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\n" +
                                        "  \"data\" : {\n" +
                                        "    \"type\" : \"crash\",\n" +
                                        "    \"id\" : 10047871\n" +
                                        "  }\n" +
                                        "}")
                        )
        );

        Exception e = new Exception("Basic exception");
        SamebugProxy samebugProxy = new SamebugProxy();
        CrashResponse response = samebugProxy.getSamebugRequest(e);

        assertThat(response.getData().getId()).isEqualTo(10047871);
    }
}
