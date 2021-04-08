package io.github.sdacode;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

public class SecretsManagerPropertySourceTest {

    @Test
    public void testProperty() {
        final String ID = "AwsSecretsManager::test", EXPECTED = "success";
        var client = Mockito.mock(AWSSecretsManager.class);
        var response = new GetSecretValueResult().withSecretString(EXPECTED);
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
