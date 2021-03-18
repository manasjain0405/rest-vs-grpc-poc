package io.github.aggregator.entity;

import java.util.List;

public class Pojo {
    List<Vehicle> vehicleList;

    public Pojo() {
    }

    public Pojo(final List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(final List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
