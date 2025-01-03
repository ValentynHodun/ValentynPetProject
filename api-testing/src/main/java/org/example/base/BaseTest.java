package org.example.base;

import org.example.config.ApiBaseTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = {ApiBaseTestConfig.class})
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    Environment environment;

    public String getApiKey() {
        return environment.getProperty("api.key");
    }

    public String getBaseUrl() {
        return environment.getProperty("api.url.dev");
    }

}
