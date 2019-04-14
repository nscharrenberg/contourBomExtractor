package com.nscharrenberg.contour.domain;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product_product")
@NamedQueries({
        @NamedQuery(name = "product.findAll", query = "SELECT p FROM Product p ORDER BY p.id ASC"),
        @NamedQuery(name = "product.findById", query = "SELECT p FROM Product p WHERE p.id = :id ORDER BY p.id ASC")
})
public class Product extends RecursiveTreeObject<Product> {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "default_code")
    private String defaultCode;

    @Column(name = "active")
    private Boolean active;

    @Column(nullable = false, name = "product_tmpl_id")
    private Integer productTmplId;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "volume")
    private double volume;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "message_last_post")
    private LocalDateTime messageLastPost;

    @Column(name = "activity_date_deadline")
    private Date activityDateDeadline;

    @Column(name = "create_uid")
    private Integer createUid;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "write_uid")
    private Integer writeUid;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @OneToMany(mappedBy = "product")
    private Set<Bom> boms;

    @OneToMany(mappedBy = "product")
    private Set<BomLine> bomLines;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefaultCode() {
        return defaultCode;
    }

    public void setDefaultCode(String defaultCode) {
        this.defaultCode = defaultCode;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getProductTmplId() {
        return productTmplId;
    }

    public void setProductTmplId(Integer productTmplId) {
        this.productTmplId = productTmplId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public LocalDateTime getMessageLastPost() {
        return messageLastPost;
    }

    public void setMessageLastPost(LocalDateTime messageLastPost) {
        this.messageLastPost = messageLastPost;
    }

    public Date getActivityDateDeadline() {
        return activityDateDeadline;
    }

    public void setActivityDateDeadline(Date activityDateDeadline) {
        this.activityDateDeadline = activityDateDeadline;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getWriteUid() {
        return writeUid;
    }

    public void setWriteUid(Integer writeUid) {
        this.writeUid = writeUid;
    }

    public LocalDateTime getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }

    public Set<Bom> getBoms() {
        return boms;
    }

    public void setBoms(Set<Bom> boms) {
        this.boms = boms;
    }

    public Set<BomLine> getBomLines() {
        return bomLines;
    }

    public void setBomLines(Set<BomLine> bomLines) {
        this.bomLines = bomLines;
    }

    @Override
    public String toString() {
        return String.format("id:%s default_code:%s productTmplId:%s barcode:%s volume:%s weight:%s", id, defaultCode, productTmplId, barcode, volume, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }

        Product p = (Product) o;

        if(
                this.getId().equals(p.getId()) &&
                        this.getDefaultCode().equals(p.getDefaultCode()) &&
                        this.isActive().equals(p.isActive()) &&
                        this.getBarcode().equals(p.getBarcode()) &&
                        this.getProductTmplId().equals(p.getProductTmplId()) &&
                        this.getVolume() == p.getVolume() &&
                        this.getCreateUid().equals(p.getCreateUid()) &&
                        this.getCreateDate().equals(p.getCreateDate()) &&
                        this.getWriteUid().equals(p.getWriteUid()) &&
                        this.getWriteDate().equals(p.getWriteDate()) &&
                        this.getBomLines().size() == p.getBomLines().size() &&
                        this.getBoms().size() == p.getBoms().size() &&
                        this.getMessageLastPost().equals(p.getMessageLastPost()) &&
                        this.getActivityDateDeadline().equals(p.getActivityDateDeadline()) &&
                        this.getWeight().equals(p.getWeight())

                ) {
            return true;
        }

        return false;
    }
}
