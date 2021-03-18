package io.github.aggregator.entity;

public class Truck extends Vehicle{
    private String name;
    private String speed;
    private String capacity;

    public Truck() {
    }

    public Truck(final int id, final String type, final String name, final String speed, final String capacity) {
        super(id, type);
        this.name = name;
        this.speed = speed;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(final String speed) {
        this.speed = speed;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(final String capacity) {
        this.capacity = capacity;
    }
}
