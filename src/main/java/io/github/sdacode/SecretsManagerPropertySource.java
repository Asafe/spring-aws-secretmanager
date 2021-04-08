package io.github.sdacode;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import io.github.sdacode.exception.SecretsManagerPropertyNotFoundException;
import org.springframework.core.env.PropertySource;

public class SecretsManagerPropertySource extends PropertySource.StubPropertySource {

    private static final String PROPERTY_SOURCE_NAME = "AwsSecretsManagerPropertySource";
    private static final String PROPERTY_PATTERN = "AwsSecretsManager::";
    private final AWSSecretsManager client;

    SecretsManagerPropertySource(AWSSecretsManager client) {
        super(PROPERTY_SOURCE_NAME);
        this.client = client;
    }

    @Override
    public String getProperty(String property) {
        if (!property.startsWith(PROPERTY_PATTERN)) return null;
        var name = property.split(PROPERTY_PATTERN)[1];
        try {
            var request = new GetSecretValueRequest().withSecretId(name);
            return client.getSecretValue(request).getSecretString();
        } catch (ResourceNotFoundException e) {
            throw new SecretsManagerPropertyNotFoundException(property, e);
        }
    }

}
