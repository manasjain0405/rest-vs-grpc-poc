package io.github.aggregator.service;

import io.github.aggregator.entity.Bus;
import io.github.aggregator.entity.Car;
import io.github.aggregator.entity.Pojo;
import io.github.aggregator.entity.Truck;
import io.github.aggregator.entity.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JacksonTestService {


    public String createObjectsAndSend() throws JsonProcessingException {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Bus(156, "Bus", "Force 501", "5 P" ));
        vehicleList.add(new Truck(158, "Truck", "Hummer", "70km", "100 Ton"));
        vehicleList.add(new Car(123, "Car", "Grand Punto", "seedan"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.writeValueAsString(vehicleList);
    }

    public Pojo createDo() {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Bus(156, "Bus", "Force 501", "5 P" ));
        vehicleList.add(new Truck(158, "Truck", "Hummer", "70km", "100 Ton"));
        vehicleList.add(new Car(123, "Car", "Grand Punto", "seedan"));
        return new Pojo(vehicleList);
    }
}
