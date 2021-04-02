package io.github.sdacode;


import io.github.sdacode.util.MockEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.Ordered;

public class SecretsManagerPostProcessorTest {

    @Test
    public void testSecretsManagerDisable() {
        var env = new MockEnvironment();
        env.setProperty("aws.secretsmanager.enable", Boolean.FALSE.toString());
        var sizePropertySourcesExpected = env.getPropertySources().size();
        var processor = new SecretsManagerPostProcessor();
        processor.postProcessEnvironment(env, null);
        Assertions.assertEquals(sizePropertySourcesExpected, env.getPropertySources().size());
    }

    @Test
    public void testSecretsManagerEnable() {
        var env = new MockEnvironment();
        env.setProperty("aws.secretsmanager.enable", Boolean.TRUE.toString());
        var sizePropertySourcesExpected = env.getPropertySources().size() + 1;
        var processor = new SecretsManagerPostProcessor();
        processor.postProcessEnvironment(env, null);
        Assertions.assertEquals(sizePropertySourcesExpected, env.getPropertySources().size());
    }

    @Test
    public void testOrder() {
        Assertions.assertEquals(Ordered.LOWEST_PRECEDENCE, new SecretsManagerPostProcessor().getOrder());
    }


}
