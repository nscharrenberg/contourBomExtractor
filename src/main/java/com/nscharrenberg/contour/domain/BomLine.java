package com.nscharrenberg.contour.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mrp_bom_line")
@NamedQueries({
        @NamedQuery(name = "bomLine.findAll", query = "SELECT p FROM BomLine p ORDER BY p.id ASC, p.product.id ASC, p.bom.id ASC"),
        @NamedQuery(name = "bomLine.findById", query = "SELECT p FROM BomLine p WHERE p.id = :id ORDER BY p.id ASC, p.product.id ASC, p.bom.id ASC")
})
public class BomLine {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "product_qty", nullable = false)
    private BigDecimal productQty;

    @Column(name = "product_uom_id", nullable = false)
    private int productUomId;

    @Column(name = "sequence")
    private int sequence;

    @Column(name = "routing_id")
    private int routingId;

    @ManyToOne
    @JoinColumn(name = "bom_id", nullable = false)
    private Bom bom;

    @Column(name = "operation_id")
    private int operationId;

    @Column(name = "create_uid")
    private int createUid;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "write_uid")
    private int writeUid;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    public BomLine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getProductUomId() {
        return productUomId;
    }

    public void setProductUomId(int productUomId) {
        this.productUomId = productUomId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getRoutingId() {
        return routingId;
    }

    public void setRoutingId(int routingId) {
        this.routingId = routingId;
    }

    public Bom getBom() {
        return bom;
    }

    public void setBom(Bom bom) {
        this.bom = bom;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
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


}
