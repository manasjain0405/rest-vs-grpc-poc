package io.github.aggregator.controller;

import io.github.aggregator.service.GrpcSquareUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/test")
public class AggController {


    @Autowired
    private GrpcSquareUtils squareUtils;

    private final RestTemplate restTemplate;

    public AggController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/rest/{number}")
    public List<Long> getSquaresFromRest(@PathVariable("number") Long number) {
        return LongStream.range(0, number+1)
                .map( no -> this.restTemplate.getForObject("http://localhost:8081/rest/square/"+no, Long.class))
                .boxed()
                .collect(Collectors.toList());
    }

    @GetMapping("/grpc/unary/{number}")
    public List<Long> getSquaresFromGrpcUnary(@PathVariable("number") Long number) {
        return LongStream.range(0, number+1)
                .map(no -> squareUtils.getSquare(no))
                .boxed()
                .collect(Collectors.toList());
    }
}
