package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:environment/${target}/application.properties"})
@Import(ApiBaseTestConfig.class)
public class UiBaseTestConfig {
}
