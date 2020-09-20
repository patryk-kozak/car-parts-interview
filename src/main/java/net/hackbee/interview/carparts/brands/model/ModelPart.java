package net.hackbee.interview.carparts.brands.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class ModelPart {

    private String name;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("description", description)
                .append("price", price)
                .toString();
    }
}
