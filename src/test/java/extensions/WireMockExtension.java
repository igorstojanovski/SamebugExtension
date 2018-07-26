package extensions;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.extension.*;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockExtension implements BeforeAllCallback, AfterEachCallback, AfterAllCallback, ParameterResolver {

    private WireMockServer wireMockServer;

    public void afterAll(ExtensionContext extensionContext) {
        wireMockServer.stop();
    }

    public void afterEach(ExtensionContext extensionContext) {
        wireMockServer.resetAll();
    }

    public void beforeAll(ExtensionContext extensionContext) {
        wireMockServer = new WireMockServer(options().port(8080));
        wireMockServer.start();
    }

    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return WireMockServer.class.equals(parameterContext.getParameter().getType());
    }

    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        if (WireMockServer.class.equals(parameterContext.getParameter().getType())) {
            return wireMockServer;
        }
        return null;
    }
}
