package io.github.sdacode;

import com.amazonaws.xray.interceptors.TracingInterceptor;
import java.util.*;
import org.springframework.core.env.ConfigurableEnvironment;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;

public class SecretsManagerProperties {

    private static final String ENABLE = "aws.secretsmanager.enable";
    private static final String REGION = "aws.secretsmanager.region";
    private static final String XRAY = "aws.secretsmanager.xray";

    private static final Map<String, Object> properties;
    private static final ClientOverrideConfiguration defaultConf;
    private static final ClientOverrideConfiguration xrayConf;


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
        properties.put(XRAY, Boolean.FALSE.toString());
        properties.put(REGION, Region.US_EAST_1.toString());

        defaultConf = ClientOverrideConfiguration
                .builder()
                .build();

        xrayConf = ClientOverrideConfiguration.builder()
                .addExecutionInterceptor(new TracingInterceptor())
                .build();
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

    public static Region getRegion() {
        return Region.of(properties.get(REGION).toString());
    }

    public static ClientOverrideConfiguration getXrayConfiguration() {
        return Boolean.valueOf(properties.get(XRAY).toString()) ? xrayConf : defaultConf;
    }

}
