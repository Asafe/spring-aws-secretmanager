package io.github.sdacode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import static io.github.sdacode.SecretsManagerProperties.getRegion;
import static io.github.sdacode.SecretsManagerProperties.getXrayConfiguration;


public class SecretsManagerPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        SecretsManagerProperties.read(environment);
        if (!SecretsManagerProperties.isEnable()) return;
        var client = SecretsManagerClient
                .builder()
                .overrideConfiguration(getXrayConfiguration())
                .region(getRegion())
                .build();
        environment
                .getPropertySources()
                .addFirst(new SecretsManagerPropertySource(client));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
