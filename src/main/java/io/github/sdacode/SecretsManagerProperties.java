package io.github.sdacode;

import com.amazonaws.regions.Regions;
import java.util.*;
import org.springframework.core.env.ConfigurableEnvironment;

public class SecretsManagerProperties {

    private static final String ENABLE = "aws.secretsmanager.enable";
    private static final String REGION = "aws.secretsmanager.region";
    private static final Map<String, Object> properties;


    private SecretsManagerProperties() {
        /**
         * Constructor
         */
    }

    /**
     * Default Properties and Configuration
     */

    static {
        properties = new HashMap<>();
        properties.put(ENABLE, Boolean.TRUE.toString());
        properties.put(REGION, Regions.US_EAST_1.getName());
    }

    /**
     * Read properties from environment and update in map
     *
     * @param environment
     */

    static void read(ConfigurableEnvironment environment) {
        Set<String> keys = new HashSet<>(properties.keySet());
        keys.forEach(key -> {
            if (environment.containsProperty(key) && Objects.nonNull(environment.getProperty(key)))
                properties.put(key, environment.getProperty(key, String.class));
        });
    }

    public static Boolean isEnable() {
        return Boolean.valueOf(properties.get(ENABLE).toString());
    }

    public static Regions getRegion() {
        return Regions.fromName(properties.get(REGION).toString());
    }

}
