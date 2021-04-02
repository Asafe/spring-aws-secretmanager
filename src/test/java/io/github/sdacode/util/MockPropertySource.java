package io.github.sdacode.util;

import java.util.Map;
import java.util.Properties;
import org.springframework.core.env.PropertiesPropertySource;

public class MockPropertySource extends PropertiesPropertySource {
    public static final String MOCK_PROPERTIES_PROPERTY_SOURCE_NAME = "mockProperties";

    public MockPropertySource() {
        this(new Properties());
    }

    public MockPropertySource(String name) {
        this(name, new Properties());
    }

    public MockPropertySource(Properties properties) {
        this("mockProperties", properties);
    }

    public MockPropertySource(String name, Properties properties) {
        super(name, properties);
    }

    public void setProperty(String name, Object value) {
        ((Map) this.source).put(name, value);
    }

    public MockPropertySource withProperty(String name, Object value) {
        this.setProperty(name, value);
        return this;
    }
}