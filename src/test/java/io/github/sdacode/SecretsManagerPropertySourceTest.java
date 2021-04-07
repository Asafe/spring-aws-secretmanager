package io.github.sdacode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class SecretsManagerPropertySourceTest {

    @Test
    public void testProperty() {
        final String ID = "AwsSecretsManager::test", EXPECTED = "success";
        var client = Mockito.mock(SecretsManagerClient.class);
        var response = GetSecretValueResponse
                .builder()
                .secretString(EXPECTED)
                .build();
        Mockito
                .when(client.getSecretValue(ArgumentMatchers.any(GetSecretValueRequest.class)))
                .thenReturn(response);

        Assertions.assertEquals(EXPECTED, new SecretsManagerPropertySource(client).getProperty(ID));
    }

    @Test
    public void testPropertyInvalid() {
        Assertions.assertNull(new SecretsManagerPropertySource(null).getProperty("test-notfound"));
    }

}
