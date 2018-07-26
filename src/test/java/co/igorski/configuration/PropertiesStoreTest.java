package co.igorski.configuration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PropertiesStoreTest {

    private static final String EMPTY_PROPERTIES_FILE = "empty.properties";
    private static final String TEST_PROPERTIES_FILE = "test.properties";
    private static final String BUILD_PROPERTIES_FILE = "build.properties";
    private static final String SAMEBUG_PROPERTIES = "samebug.properties";

    @Test
    public void endpointDefaultValueShouldExist() {
        PropertiesStore propertiesStore = new PropertiesStore(EMPTY_PROPERTIES_FILE,
                new BuildProperties(BUILD_PROPERTIES_FILE));
        assertThat(propertiesStore.getEndpoint()).isEqualTo("https://nightly.samebug.com/rest");
    }

    @Test
    public void apiKeyDefaultShouldBeEmpty() {
        PropertiesStore propertiesStore = new PropertiesStore(EMPTY_PROPERTIES_FILE,
                new BuildProperties(BUILD_PROPERTIES_FILE));
        assertThat(propertiesStore.getApiKey()).isEmpty();
    }

    @Test
    public void shouldSetEndpointFromGivenPropertiesFile() {
        PropertiesStore propertiesStore = new PropertiesStore(TEST_PROPERTIES_FILE,
                new BuildProperties(BUILD_PROPERTIES_FILE));
        assertThat(propertiesStore.getEndpoint()).isEqualTo("http://endpoint");
    }

    @Test
    public void shouldSetApiKeyFromGivenPropertiesFile() {
        PropertiesStore propertiesStore = new PropertiesStore(TEST_PROPERTIES_FILE,
                new BuildProperties(BUILD_PROPERTIES_FILE));
        assertThat(propertiesStore.getApiKey()).isEqualTo("apiKey");
    }

    @Test
    public void shouldSetEndpointFromDefaultPropertiesFile() {
        PropertiesStore propertiesStore = new PropertiesStore();
        assertThat(propertiesStore.getEndpoint()).isEqualTo("http://localhost:8080");
    }

    @Test
    public void shouldSetApiKeyFromDefaultPropertiesFile() {
        PropertiesStore propertiesStore = new PropertiesStore();
        assertThat(propertiesStore.getApiKey()).isEqualTo("apiKey");
    }

    @Test
    public void shouldReturnEmptyWhenValueDoesNotExist() {
        PropertiesStore propertiesStore = new PropertiesStore(SAMEBUG_PROPERTIES,
                new BuildProperties(BUILD_PROPERTIES_FILE));
        assertThat(propertiesStore.getApiKey()).isEqualTo("");
    }

    @Test
    public void shouldGetCorrectBuildVersion() {
        PropertiesStore propertiesStore = new PropertiesStore(SAMEBUG_PROPERTIES,
                new BuildProperties(BUILD_PROPERTIES_FILE));
        assertThat(propertiesStore.getBuildVersion()).isEqualTo("1.0.0");
    }

}