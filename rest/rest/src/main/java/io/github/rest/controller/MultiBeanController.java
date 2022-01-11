package io.github.rest.controller;

import io.github.rest.service.TestBeanRepo;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiBean")
public class MultiBeanController {

    @Autowired
    private TestBeanRepo testBeanRepo;

    @GetMapping("/bean1")
    public void b1() {
        testBeanRepo.getBean("testImpl1").printLog();
    }

    @GetMapping("/bean2")
    public void b2() {
        testBeanRepo.getBean("testImpl2").printLog();
    }

}
