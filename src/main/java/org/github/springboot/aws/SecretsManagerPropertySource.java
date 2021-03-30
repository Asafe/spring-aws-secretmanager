package org.github.springboot.aws;

import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertySource;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

import static org.github.springboot.aws.SecretsManagerProperties.getRegion;

public class SecretsManagerPropertySource extends PropertySource.StubPropertySource {

    private static final String PROPERTY_SOURCE_NAME = "AwsSecretsManagerPropertySource";
    private static final String PROPERTY_PATTERN = "AwsSecretsManager::";

    SecretsManagerPropertySource() {
        super(PROPERTY_SOURCE_NAME);
    }

    @Override
    public String getProperty(String property) {
        if (!property.startsWith(PROPERTY_PATTERN)) return null;
        var name = property.split(PROPERTY_PATTERN)[1];
        try (var client = SecretsManagerClient.builder().region(getRegion()).build()) {
            var request = GetSecretValueRequest.builder().secretId(name).build();
            return client.getSecretValue(request).secretString();
        } catch (SecretsManagerException e) {
            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
            return null;
        }
    }

}
