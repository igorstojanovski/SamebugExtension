package org.igorski.configuration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PropertiesStoreTest {

    @Test
    public void endpointDefaultValueShouldExist() {
        PropertiesStore propertiesStore = new PropertiesStore("empty.properties");
        assertThat(propertiesStore.getEndpoint()).isEqualTo("https://nightly.samebug.com/rest");
    }

    @Test
    public void apiKeyDefaultShouldBeEmpty() {
        PropertiesStore propertiesStore = new PropertiesStore("empty.properties");
        assertThat(propertiesStore.getApiKey()).isEmpty();
    }

    @Test
    public void shouldSetEndpointFromGivenPropertiesFile() {
        PropertiesStore propertiesStore = new PropertiesStore("test.properties");
        assertThat(propertiesStore.getEndpoint()).isEqualTo("http://endpoint");
    }

    @Test
    public void shouldSetApiKeyFromGivenPropertiesFile() {
        PropertiesStore propertiesStore = new PropertiesStore("test.properties");
        assertThat(propertiesStore.getApiKey()).isEqualTo("apiKey");
    }

    @Test
    public void shouldSetEndpointFromDefaultPropertiesFile() {
        PropertiesStore propertiesStore = new PropertiesStore();
        assertThat(propertiesStore.getEndpoint()).isEqualTo("http://endpoint");
    }

    @Test
    public void shouldSetApiKeyFromDefaultPropertiesFile() {
        PropertiesStore propertiesStore = new PropertiesStore();
        assertThat(propertiesStore.getApiKey()).isEqualTo("apiKey");
    }
}