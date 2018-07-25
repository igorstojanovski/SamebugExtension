package co.igorski.configuration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BuildPropertiesTest {

    @Test
    public void shouldRetrieveProperty() {
        BuildProperties buildProperties = new BuildProperties("build.properties");
        assertThat(buildProperties.getBuildVersion()).isEqualTo("1.0.0");
    }

    @Test
    public void shouldReplaceNonFilteredWithDefault() {
        BuildProperties buildProperties = new BuildProperties("build-non-filtered.properties");
        assertThat(buildProperties.getBuildVersion()).isEqualTo("<undefined version>");
    }

    @Test
    public void shouldReplaceEmptyWithDefault() {
        BuildProperties buildProperties = new BuildProperties("build-empty.properties");
        assertThat(buildProperties.getBuildVersion()).isEqualTo("<undefined version>");
    }

    @Test
    public void shouldSetDefaultWhenFileDoesNotExist() {
        BuildProperties buildProperties = new BuildProperties("non-existant.properties");
        assertThat(buildProperties.getBuildVersion()).isEqualTo("<undefined version>");
    }
}