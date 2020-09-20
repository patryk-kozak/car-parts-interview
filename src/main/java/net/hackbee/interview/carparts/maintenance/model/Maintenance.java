package net.hackbee.interview.carparts.maintenance.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class Maintenance {

    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("beginDate", beginDate)
                .append("endDate", endDate)
                .toString();
    }
}
