package io.github.sdacode;

import com.amazonaws.services.secretsmanager.AWSSecretsManagerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import static io.github.sdacode.SecretsManagerProperties.getRegion;


public class SecretsManagerPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        SecretsManagerProperties.read(environment);
        if (!SecretsManagerProperties.isEnable()) return;
        var client = AWSSecretsManagerClient
                .builder()
                .standard()
                .withRegion(getRegion())
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
