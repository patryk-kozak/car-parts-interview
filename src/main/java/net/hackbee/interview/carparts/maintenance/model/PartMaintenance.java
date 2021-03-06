package net.hackbee.interview.carparts.maintenance.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class PartMaintenance {

    private String name;
    private Long partId;
    private LocalDate beginDate;
    private LocalDate endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
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
                .append("partId", partId)
                .append("beginDate", beginDate)
                .append("endDate", endDate)
                .toString();
    }
}
