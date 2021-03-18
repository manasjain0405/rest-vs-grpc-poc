package io.github.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration
@PropertySource("classpath:/testDir/test.yaml")
public class TestConfig {

    @Value( "${mytest.str}" )
    private String fromProps;

    @Bean("test_config")
    public String testConfig() {
        return fromProps;
    }
}
