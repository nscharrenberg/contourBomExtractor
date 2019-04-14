package com.nscharrenberg.contour.domain;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mrp_bom_line")
@NamedQueries({
        @NamedQuery(name = "bomLine.findAll", query = "SELECT p FROM BomLine p ORDER BY p.id ASC, p.product.id ASC, p.bom.id ASC"),
        @NamedQuery(name = "bomLine.findById", query = "SELECT p FROM BomLine p WHERE p.id = :id ORDER BY p.id ASC, p.product.id ASC, p.bom.id ASC")
})
public class BomLine extends RecursiveTreeObject<BomLine> {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "product_qty", nullable = false)
    private BigDecimal productQty;

    @Column(name = "product_uom_id", nullable = false)
    private Integer productUomId;

    @Column(name = "sequence")
    private Integer sequence;

    @Column(name = "routing_id")
    private Integer routingId;

    @ManyToOne
    @JoinColumn(name = "bom_id", nullable = false)
    private Bom bom;

    @Column(name = "operation_id")
    private Integer operationId;

    @Column(name = "create_uid")
    private Integer createUid;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "write_uid")
    private Integer writeUid;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    public BomLine() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getProductQty() {
        return productQty;
    }

    public void setProductQty(BigDecimal productQty) {
        this.productQty = productQty;
    }

    public Integer getProductUomId() {
        return productUomId;
    }

    public void setProductUomId(Integer productUomId) {
        this.productUomId = productUomId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getRoutingId() {
        return routingId;
    }

    public void setRoutingId(Integer routingId) {
        this.routingId = routingId;
    }

    public Bom getBom() {
        return bom;
    }

    public void setBom(Bom bom) {
        this.bom = bom;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
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

    @Override
    public String toString() {
        return String.format("id:%s productQty:%s productUomId:%s sequence:%s routingId:%s", id, productQty, productUomId, sequence, routingId);
    }
}
