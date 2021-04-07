package io.github.sdacode;

import io.github.sdacode.util.MockEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;

public class SecretsManagerPropertiesTest {

    @Test
    public void test() {
        //Default properties
        Assertions.assertEquals(Region.US_EAST_1, SecretsManagerProperties.getRegion());
        Assertions.assertEquals(0, SecretsManagerProperties.getXrayConfiguration().executionInterceptors().size());
        //Mock ENV
        var env = new MockEnvironment();
        env.setProperty("aws.secretsmanager.region", "sa-east-1");
        env.setProperty("aws.secretsmanager.xray", "true");
        //Update properties and test
        SecretsManagerProperties.read(env);
        Assertions.assertEquals(Region.SA_EAST_1, SecretsManagerProperties.getRegion());
        Assertions.assertEquals(1, SecretsManagerProperties.getXrayConfiguration().executionInterceptors().size());
    }

}
