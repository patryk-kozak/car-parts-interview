package net.hackbee.interview.carparts.brands.model;

import java.util.ArrayList;
import java.util.List;

public class BrandProfile {

    private final String name;
    private List<Model> models = new ArrayList<>();

    public BrandProfile(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("name", name)
                .append("models", models)
                .toString();
    }
}
