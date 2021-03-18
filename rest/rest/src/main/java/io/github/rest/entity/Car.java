package io.github.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car extends Vehicle{
    private String model;
    private String modelType;

    public Car(final int id, final String type, final String model, final String modelType) {
        super(id, type);
        this.model = model;
        this.modelType = modelType;
    }

    public Car() {
        super("Car");
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(final String modelType) {
        this.modelType = modelType;
    }
}