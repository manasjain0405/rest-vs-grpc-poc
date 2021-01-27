package io.github.rest.controller;

import io.github.rest.service.SquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class SquareController {

    @Autowired
    SquareService squareService;

    @GetMapping("/square/{no}")
    public Integer squareIt(@PathVariable("no") Integer no){
        return squareService.getSquare(no);
    }

}
