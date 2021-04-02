package io.github.sdacode.util;

import org.springframework.core.env.AbstractEnvironment;

public class MockEnvironment extends AbstractEnvironment {
    private MockPropertySource propertySource = new MockPropertySource();

    public MockEnvironment() {
        this.getPropertySources().addLast(this.propertySource);
    }

    public void setProperty(String key, String value) {
        this.propertySource.setProperty(key, value);
    }

    public MockEnvironment withProperty(String key, String value) {
        this.setProperty(key, value);
        return this;
    }
}

