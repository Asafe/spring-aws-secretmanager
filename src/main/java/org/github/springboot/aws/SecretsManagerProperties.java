package org.github.springboot.aws;

import java.util.*;
import org.springframework.core.env.ConfigurableEnvironment;
import software.amazon.awssdk.regions.Region;

public class SecretsManagerProperties {

    private static final String ENABLE = "secrets.manager.enable";
    private static final String REGION = "secrets.manager.region";
    private static final Map<String, Object> properties;

    private SecretsManagerProperties() {
        /**
         * Constructor
         */
    }

    static {
        /**
         * Default Properties
         */
        properties = new HashMap<>();
        properties.put(ENABLE, Boolean.TRUE.toString());
        properties.put(REGION, Region.US_EAST_1.toString());
    }

    /**
     * Read properties from environment and update in map
     *
     * @param environment
     */

    public static void read(ConfigurableEnvironment environment) {
        Set<String> keys = new HashSet<>(properties.keySet());
        keys.forEach(key -> {
            if (environment.containsProperty(key) && Objects.nonNull(environment.getProperty(key)))
                properties.put(key, environment.getProperty(key, String.class));
        });
    }

    public static Boolean isEnable() {
        return Boolean.parseBoolean(properties.get(ENABLE).toString());
    }

    public static Region getRegion() {
        return Region.of(properties.get(REGION).toString());
    }

}
