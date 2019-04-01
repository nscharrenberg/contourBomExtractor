package com.nscharrenberg.contour.domain;

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
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "default_code")
    private String defaultCode;

    @Column(name = "active")
    private boolean active;

    @Column(nullable = false, name = "product_tmpl_id")
    private int productTmplId;

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
    private int createUid;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "write_uid")
    private int writeUid;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @OneToMany(mappedBy = "product")
    private Set<Bom> boms;

    @OneToMany(mappedBy = "product")
    private Set<BomLine> bomLines;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefaultCode() {
        return defaultCode;
    }

    public void setDefaultCode(String defaultCode) {
        this.defaultCode = defaultCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getProductTmplId() {
        return productTmplId;
    }

    public void setProductTmplId(int productTmplId) {
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

    public int getCreateUid() {
        return createUid;
    }

    public void setCreateUid(int createUid) {
        this.createUid = createUid;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public int getWriteUid() {
        return writeUid;
    }

    public void setWriteUid(int writeUid) {
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
}
