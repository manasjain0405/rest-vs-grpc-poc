package io.github.rest.controller;

import io.github.rest.entity.Vehicle;
import io.github.rest.service.SquareService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class SquareController {

    @Autowired
    SquareService squareService;

    @GetMapping("/square/{no}")
    public List<List<Integer>> squareIt(@PathVariable("no") Integer no){
        return squareService.getSquare(no);
    }

    @GetMapping("/jktest")
    public String justDoIt(){
        try {
            return squareService.doIt();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @GetMapping("/jktest2")
    public List<Vehicle> justDoItAgain(){
        return squareService.doItAgain();
    }

}
