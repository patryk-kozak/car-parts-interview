package net.hackbee.interview.carparts.brands.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("parts", parts)
                .toString();
    }
}
