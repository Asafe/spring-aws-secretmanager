package io.github.sdacode;

import io.github.sdacode.util.MockEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;

public class SecretsManagerPropertiesTest {

    @Test
    public void testRegion() {
        Assertions.assertEquals(Region.US_EAST_1, SecretsManagerProperties.getRegion());
        var env = new MockEnvironment();
        env.setProperty("aws.secretsmanager.region", "sa-east-1");
        SecretsManagerProperties.read(env);
        Assertions.assertEquals(Region.SA_EAST_1, SecretsManagerProperties.getRegion());
    }

}
