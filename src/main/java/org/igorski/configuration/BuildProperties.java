package org.igorski.configuration;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.TempDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ExtendWith(TempDirectory.class)
class BuildProperties {
    static final String DEFAULT_BUILD_VERSION = "<undefined version>";
    static final String BUILD_VERSION_KEY = "version";
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildProperties.class);
    private String buildVersion;

    BuildProperties(String buildPropertiesFile) {
        var properties = new Properties();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(buildPropertiesFile)) {
            properties.load(in);
        } catch (IOException | NullPointerException e) {
            LOGGER.warn("Failed to read client properties file!", e);
        }
        buildVersion = properties.getProperty(BUILD_VERSION_KEY, DEFAULT_BUILD_VERSION);
        if (buildVersion.contains("${project.version}")) {
            buildVersion = DEFAULT_BUILD_VERSION;
        }
    }

    public String getBuildVersion() {
        return buildVersion;
    }
}