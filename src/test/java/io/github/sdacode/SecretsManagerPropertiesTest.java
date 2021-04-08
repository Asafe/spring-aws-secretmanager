package io.github.sdacode;

import com.amazonaws.regions.Regions;
import io.github.sdacode.util.MockEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SecretsManagerPropertiesTest {

    @Test
    public void test() {
        //Default properties
        Assertions.assertEquals(Regions.US_EAST_1, SecretsManagerProperties.getRegion());
        //Mock ENV
        var env = new MockEnvironment();
        env.setProperty("aws.secretsmanager.region", "sa-east-1");
        //Update properties and test
        SecretsManagerProperties.read(env);
        Assertions.assertEquals(Regions.SA_EAST_1, SecretsManagerProperties.getRegion());
    }

}
