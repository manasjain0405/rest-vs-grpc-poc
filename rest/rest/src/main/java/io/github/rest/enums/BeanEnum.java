package io.github.rest.enums;

import io.github.rest.dto.BeanConfig;

public enum BeanEnum {
    BEAN1{
        @Override
        String beanName() {
            return BeanConfig.bean1name;
        }
    }, BEAN2{
        @Override
        String beanName() {
            return BeanConfig.bean2name;
        }
    };

    abstract String beanName();
}
