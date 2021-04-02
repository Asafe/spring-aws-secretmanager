package io.github.sdacode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SecretsManagerPropertySourceTest {

    @Test
    public void testPropertyNotFound() {
        Assertions.assertNull(new SecretsManagerPropertySource().getProperty("test-notfound"));
    }

}
