package co.igorski.services;

import co.igorski.model.CrashResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import extensions.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(WireMockExtension.class)
public class SamebugProxyIT {

    @Test
    public void shouldSendCorrectContentToServer(WireMockServer server) {
        server.stubFor(
                post(urlEqualTo("/crashes"))
                        .withHeader("X-Samebug-ApiKey", containing("apiKey"))
                        .withHeader("User-Agent", containing("JUnit-Extension/1.0.0"))
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
