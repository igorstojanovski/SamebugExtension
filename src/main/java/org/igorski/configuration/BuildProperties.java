package org.igorski.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

class BuildProperties {
    static final String BUILD_PROPERTIES_FILE = "build.properties";
    static final String DEFAULT_BUILD_VERSION = "<undefined version>";
    static final String BUILD_VERSION_KEY = "version";
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildProperties.class);
    private String buildVersion;

    BuildProperties(String buildPropertiesFile) {
        var p = new Properties();
        try {
            p.load(getClass().getClassLoader().getResourceAsStream(buildPropertiesFile));
        } catch (IOException e) {
            LOGGER.warn("Failed to read client properties file!", e);
        }
        buildVersion = p.getProperty(BUILD_VERSION_KEY, DEFAULT_BUILD_VERSION);
        if (buildVersion.contains("${project.version}")) {
            buildVersion = DEFAULT_BUILD_VERSION;
        }
    }

    public String getBuildVersion() {
        return buildVersion;
    }
}