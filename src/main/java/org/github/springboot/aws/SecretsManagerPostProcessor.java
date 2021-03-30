package org.github.springboot.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;


public class SecretsManagerPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        SecretsManagerProperties.read(environment);
        if (!SecretsManagerProperties.isEnable()) return;
        environment.getPropertySources().addFirst(new SecretsManagerPropertySource());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
