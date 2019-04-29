package com.nscharrenberg.contour.domain;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product_product")
@NamedQueries({
        @NamedQuery(name = "product.findAll", query = "SELECT p FROM Product p ORDER BY p.id ASC"),
        @NamedQuery(name = "product.findById", query = "SELECT p FROM Product p WHERE p.id = :id ORDER BY p.id ASC"),
        @NamedQuery(name = "product.findByDefaultCode", query = "SELECT p FROM Product p WHERE p.defaultCode = :id ORDER BY p.defaultCode ASC")
})
public class Product extends RecursiveTreeObject<Product> {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "default_code")
    private String defaultCode;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "product_tmpl_id")
    private Template productTmplId;

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

    public Boolean getActive() {
        return active;
    }

    public Template getProductTmplId() {
        return productTmplId;
    }

    public void setProductTmplId(Template productTmplId) {
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
        return weight == null ? new BigDecimal(0.00) : weight;
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
        return "Product{" +
                "id=" + id +
                ", defaultCode='" + defaultCode + '\'' +
                ", productTmplId=" + productTmplId.getDefaultCode() +
                ", barcode='" + barcode + '\'' +
                ", volume=" + volume +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.volume, volume) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(defaultCode, product.defaultCode) &&
                Objects.equals(active, product.active) &&
                Objects.equals(productTmplId, product.productTmplId) &&
                Objects.equals(barcode, product.barcode) &&
                Objects.equals(weight, product.weight) &&
                Objects.equals(messageLastPost, product.messageLastPost) &&
                Objects.equals(activityDateDeadline, product.activityDateDeadline) &&
                Objects.equals(createUid, product.createUid) &&
                Objects.equals(createDate, product.createDate) &&
                Objects.equals(writeUid, product.writeUid) &&
                Objects.equals(writeDate, product.writeDate) &&
                Objects.equals(boms, product.boms) &&
                Objects.equals(bomLines, product.bomLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, defaultCode, active, productTmplId, barcode, volume, weight, messageLastPost, activityDateDeadline, createUid, createDate, writeUid, writeDate, boms, bomLines);
    }
}
