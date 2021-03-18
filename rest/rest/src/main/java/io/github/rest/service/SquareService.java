package io.github.rest.service;

import io.github.rest.entity.Bus;
import io.github.rest.entity.Car;
import io.github.rest.entity.Pojo;
import io.github.rest.entity.Truck;
import io.github.rest.entity.Vehicle;
import io.github.rest.utils.GroupingCollector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class SquareService {

    private final RestTemplate restTemplate;

    public SquareService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<List<Integer>> getSquare(int no) {
        return IntStream.range(0, no).boxed().collect(new GroupingCollector<>(5));


    }

    public String doIt() throws JsonProcessingException {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/jktest/test", String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Vehicle> vehicles = mapper.readValue(responseEntity.getBody(), mapper.getTypeFactory().constructCollectionType(
                List.class, Vehicle.class));
        vehicles.forEach(this::process);
        return responseEntity.getBody();
    }

    public List<Vehicle> doItAgain() {
        ResponseEntity<Pojo> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/jktest/test2", Pojo.class);
        Objects.requireNonNull(responseEntity.getBody()).getVehicleList().forEach(this::process);
        return Objects.requireNonNull(responseEntity.getBody()).getVehicleList();
    }

    private void process(Vehicle vehicle){
        switch (vehicle.getType()){
            case "Car" :
                Car car = (Car) vehicle;
                System.out.println("Car Name: " + car.getModel());
                break;
            case "Truck":
                Truck truck = (Truck) vehicle;
                System.out.println("Truck Name: " + truck.getName());
                break;
            case "Bus":
                Bus bus = (Bus) vehicle;
                System.out.println("Bus Name :" + bus.getName());
                break;
            default:
                System.out.println("Nota");
                break;
        }
    }

}
