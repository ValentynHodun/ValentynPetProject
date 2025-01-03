package org.example.config;

import org.springframework.context.annotation.*;

@Configuration
@PropertySource({"classpath:environment/${target}/application.properties"})
public class ApiBaseTestConfig {

}
