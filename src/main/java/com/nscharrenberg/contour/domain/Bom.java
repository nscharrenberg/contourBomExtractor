package com.nscharrenberg.contour.domain;

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
public class Bom {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "active")
    private boolean active;

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
    private int productUomId;

    @Column(name = "sequence")
    private int sequence;

    @Column(name = "routing_id")
    private int routingId;

    @Column(name = "ready_to_produce", nullable = false)
    private String readyToProduce;

    @Column(name = "picking_type_id")
    private int pickingTypeId;

    @Column(name = "company_id", nullable = false)
    private int companyId;

    @Column(name = "message_last_post")
    private LocalDateTime messageLastPost;

    @Column(name = "create_uid")
    private int createUid;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "write_uid")
    private int writeUid;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @OneToMany(mappedBy = "bom")
    private Set<BomLine> bomLines;

    public Bom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
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

    public String getReadyToProduce() {
        return readyToProduce;
    }

    public void setReadyToProduce(String readyToProduce) {
        this.readyToProduce = readyToProduce;
    }

    public int getPickingTypeId() {
        return pickingTypeId;
    }

    public void setPickingTypeId(int pickingTypeId) {
        this.pickingTypeId = pickingTypeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public LocalDateTime getMessageLastPost() {
        return messageLastPost;
    }

    public void setMessageLastPost(LocalDateTime messageLastPost) {
        this.messageLastPost = messageLastPost;
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

    public Set<BomLine> getBomLines() {
        return bomLines;
    }

    public void setBomLines(Set<BomLine> bomLines) {
        this.bomLines = bomLines;
    }
}
