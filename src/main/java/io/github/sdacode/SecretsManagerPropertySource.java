package io.github.sdacode;

import io.github.sdacode.exception.SecretsManagerPropertyNotFoundException;
import org.springframework.core.env.PropertySource;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.ResourceNotFoundException;

public class SecretsManagerPropertySource extends PropertySource.StubPropertySource {

    private static final String PROPERTY_SOURCE_NAME = "AwsSecretsManagerPropertySource";
    private static final String PROPERTY_PATTERN = "AwsSecretsManager::";
    private final SecretsManagerClient client;

    SecretsManagerPropertySource(SecretsManagerClient client) {
        super(PROPERTY_SOURCE_NAME);
        this.client = client;
    }

    @Override
    public String getProperty(String property) {
        if (!property.startsWith(PROPERTY_PATTERN)) return null;
        var name = property.split(PROPERTY_PATTERN)[1];
        try {
            var request = GetSecretValueRequest.builder().secretId(name).build();
            return client.getSecretValue(request).secretString();
        } catch (ResourceNotFoundException e) {
            throw new SecretsManagerPropertyNotFoundException(property, e);
        }
    }

}
