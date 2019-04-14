package com.nscharrenberg.contour.domain;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "mrp_bom")
@NamedQueries({
        @NamedQuery(name = "bom.findAll", query = "SELECT p FROM Bom p ORDER BY p.id DESC, p.product.id ASC"),
        @NamedQuery(name = "bom.findById", query = "SELECT p FROM Bom p WHERE p.id = :id ORDER BY p.id ASC, p.product.id ASC")
})
public class Bom extends RecursiveTreeObject<Bom> {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "product_tmpl_id", nullable = false)
    private String productTmplId;

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

    @Column(name = "ready_to_produce", nullable = false)
    private String readyToProduce;

    @Column(name = "picking_type_id")
    private Integer pickingTypeId;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Column(name = "message_last_post")
    private LocalDateTime messageLastPost;

    @Column(name = "create_uid")
    private Integer createUid;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "write_uid")
    private Integer writeUid;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @OneToMany(mappedBy = "bom")
    private Set<BomLine> bomLines;

    public Bom() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductTmplId() {
        return productTmplId;
    }

    public void setProductTmplId(String productTmplId) {
        this.productTmplId = productTmplId;
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

    public String getReadyToProduce() {
        return readyToProduce;
    }

    public void setReadyToProduce(String readyToProduce) {
        this.readyToProduce = readyToProduce;
    }

    public Integer getPickingTypeId() {
        return pickingTypeId;
    }

    public void setPickingTypeId(Integer pickingTypeId) {
        this.pickingTypeId = pickingTypeId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public LocalDateTime getMessageLastPost() {
        return messageLastPost;
    }

    public void setMessageLastPost(LocalDateTime messageLastPost) {
        this.messageLastPost = messageLastPost;
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

    public Set<BomLine> getBomLines() {
        return bomLines;
    }

    public void setBomLines(Set<BomLine> bomLines) {
        this.bomLines = bomLines;
    }

    @Override
    public String toString() {
        return String.format("id:%s code:%s type:%s productTmplId:%s productQty:%s productUomId:%s sequence:%s routingId:%s", id, code, type, productTmplId, productQty, productUomId, sequence, routingId);
    }
}
