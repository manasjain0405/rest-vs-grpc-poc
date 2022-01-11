package io.github.rest.service;

import io.github.rest.dto.BeanConfig;
import io.github.rest.enums.BeanEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@Slf4j
public class TestBeanRepo {

    private final static String bean1 = BeanEnum.BEAN1.name();

    @Autowired
    private Map<String, TestInterface> testInterfaceMap;

    public TestInterface getBean(final String key) {
        return testInterfaceMap.get(key);
    }

    @Bean(BeanConfig.bean1name)
    TestInterface getBean1() {
        return () -> log.info("Test Bean 1");
    }

    @Bean(BeanConfig.bean2name)
    TestInterface getBean2() {
        return () -> log.info("Test Bean 2");
    }
}
