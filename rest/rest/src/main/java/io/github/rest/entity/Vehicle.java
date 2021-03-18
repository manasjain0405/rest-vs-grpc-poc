package io.github.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "Car"),
        @JsonSubTypes.Type(value = Bus.class, name = "Bus"),
        @JsonSubTypes.Type(value = Truck.class, name = "Truck")})

public abstract class Vehicle{

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String type;

    public Vehicle() {
    }

    public Vehicle(final String type) {
        this.type = type;
    }

    public Vehicle(final int id, final String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}


