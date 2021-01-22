package io.github.rest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class SquareController {

    @GetMapping("/square/{no}")
    public Long squareIt(@PathVariable("no") Long no){
        System.out.println("Returned for" + no);
        return no*no;
    }

}
