package io.github.rest.entity;

public class Bus extends Vehicle{
    private String name;
    private String seatingCapacity;

    public Bus() {
        super("Bus");
    }

    public Bus(final int id, final String type, final String name, final String seatingCapacity) {
        super(id, type);
        this.name = name;
        this.seatingCapacity = seatingCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(final String seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
