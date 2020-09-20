package net.hackbee.interview.carparts.persistence.entity;

import javax.persistence.*;

/**
 * No idea what this is supposed to do, there is no description for Sale.
 * I will just make up something. Had to google what are "sales arguments" ;-)
 */
@Entity
@Table(name = "SALE_ARGUMENTS")
public class SaleArgumentEntity {

    @Id
    // NOTE: Don't use it in prod. It disables DB batch insert optimization.
    // JPA also need to load it back to context after each insert.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private PartEntity part;

    private Integer discountPercentage = 0;
    private Boolean fastDelivery = false;
    private Boolean easyInstall = false;

    public PartEntity getPart() {
        return part;
    }

    public void setPart(PartEntity part) {
        this.part = part;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getFastDelivery() {
        return fastDelivery;
    }

    public void setFastDelivery(Boolean fastDelivery) {
        this.fastDelivery = fastDelivery;
    }

    public Boolean getEasyInstall() {
        return easyInstall;
    }

    public void setEasyInstall(Boolean easyInstall) {
        this.easyInstall = easyInstall;
    }
}
