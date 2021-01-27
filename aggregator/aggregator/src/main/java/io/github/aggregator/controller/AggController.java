package io.github.aggregator.controller;

import io.github.aggregator.service.GrpcSquareUtils;
import io.manasjain0405.grpc.SquareRequest;
import io.manasjain0405.grpc.SquareResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
    public Object getSquaresFromRest(@PathVariable("number") Integer number) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= number ; i++) {
            ResponseEntity<Integer> responseEntity = this.restTemplate.getForEntity("http://localhost:8081/rest/square/"+i, Integer.class);
            map.put(i, responseEntity.getBody());
        }
        return map;
    }

    @GetMapping("/grpc/unary/{number}")
    public Object getSquaresFromGrpcUnary(@PathVariable("number") Integer number) {
        return IntStream.range(1, number+1)
                .mapToObj(no -> SquareRequest.newBuilder().setNumber(no).build())
                .map(squareUtils::getSquare)
                .collect(Collectors.toMap(SquareResponse::getNumber, SquareResponse::getResult));
    }

    @GetMapping("/grpc/stream/{number}")
    public Object getSquaresFromGrpcStream(@PathVariable("number") Integer number) throws InterruptedException {
        return squareUtils.getSquareStream(number);
    }
}
