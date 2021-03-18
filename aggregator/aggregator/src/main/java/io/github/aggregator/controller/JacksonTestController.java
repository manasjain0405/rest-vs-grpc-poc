package io.github.aggregator.controller;

import io.github.aggregator.entity.Pojo;
import io.github.aggregator.service.JacksonTestService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jktest")
public class JacksonTestController {

    @Autowired
    JacksonTestService jacksonTestService;

    @GetMapping("/test")
    public String getSquaresFromRest() {
        try {
            return jacksonTestService.createObjectsAndSend();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return " [{\n"
                    + "            \"id\":0123,\n"
                    + "                    \"model\":\"Grand Punto\",\n"
                    + "                    \"type\":\"Car\",\n"
                    + "                    \"modelType\":\"seedan\",\n"
                    + "        },\n"
                    + "        {\n"
                    + "            \"id\":0156,\n"
                    + "                \"name\":\"Hummer\",\n"
                    + "                \"type\":\"Truck\",\n"
                    + "                \"speed\":\"70km\",\n"
                    + "                \"capacity\": \"100 Ton\"\n"
                    + "        },\n"
                    + "        {\n"
                    + "            \"id\":0156,\n"
                    + "                \"name\":\"Force 501\",\n"
                    + "                \"type\":\"Bus\",\n"
                    + "                \"seatingCapacity\":\"5 person\",\n"
                    + "        }\n"
                    + "]";
        }
    }

    @GetMapping("/test2")
    public Pojo getSquaresFromRest2() {
        return jacksonTestService.createDo();
    }
}
