package net.hackbee.interview.carparts.brands.model;

import java.util.List;

public class Model {

    private final String name;
    private List<ModelPart> parts;

    public Model(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<ModelPart> getParts() {
        return parts;
    }

    public void setParts(List<ModelPart> parts) {
        this.parts = parts;
    }
}
