package com.nscharrenberg.contour.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "stuklijst")
public class BillOfMaterial {
    @Id
    @SequenceGenerator(name="stuklijst_column_id_seq",
            sequenceName="stuklijst_column_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="stuklijst_column_id_seq")
    @Column(name = "column_id", updatable=false)
    private Integer column_id;

    /**
     * Product Properties
     */
    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "product_product_default_code")
    private String product_defaultCode;

    @Column(name = "product_active")
    private Boolean product_active;

    @Column(name = "product_product_tmpl_id")
    private Integer product_productTmplId;

    @Column(name = "product_barcode")
    private String product_barcode;

    @Column(name = "product_volume")
    private double product_volume;

    @Column(name = "product_weight")
    private BigDecimal product_weight;

    @Column(name = "product_message_last_post")
    private LocalDateTime product_messageLastPost;

    @Column(name = "product_activity_date_deadline")
    private Date product_activityDateDeadline;

    @Column(name = "product_create_uid")
    private Integer product_createUid;

    @Column(name = "product_create_date")
    private LocalDateTime product_createDate;

    @Column(name = "product_write_uid")
    private Integer product_writeUid;

    @Column(name = "product_write_date")
    private LocalDateTime product_writeDate;

    /**
     * Bom Properties
     */
    @Column(name = "bom_id")
    private Integer bom_id;

    @Column(name = "bom_code")
    private String bom_code;

    @Column(name = "bom_active")
    private Boolean bom_active;

    @Column(name = "bom_type")
    private String bom_type;

    @Column(name = "bom_product_tmpl_id")
    private String bom_productTmplId;

    @Column(name = "bom_product_qty")
    private BigDecimal bom_productQty;

    @Column(name = "bom_product_uom_id")
    private Integer bom_productUomId;

    @Column(name = "bom_sequence")
    private Integer bom_sequence;

    @Column(name = "bom_routing_id")
    private Integer bom_routingId;

    @Column(name = "bom_ready_to_produce")
    private String bom_readyToProduce;

    @Column(name = "bom_picking_type_id")
    private Integer bom_pickingTypeId;

    @Column(name = "bom_company_id")
    private Integer bom_companyId;

    @Column(name = "bom_message_last_post")
    private LocalDateTime bom_messageLastPost;

    @Column(name = "bom_create_uid")
    private Integer bom_createUid;

    @Column(name = "bom_create_date")
    private LocalDateTime bom_createDate;

    @Column(name = "bom_write_uid")
    private Integer bom_writeUid;

    @Column(name = "bom_write_date")
    private LocalDateTime bom_writeDate;

    /**
     * BomLine Properties
     */
    @Column(name = "bomline_id")
    private Integer bomline_id;

    @Column(name = "bomline_product_qty")
    private BigDecimal bomline_productQty;

    @Column(name = "bomline_product_uom_id")
    private Integer bomline_productUomId;

    @Column(name = "bomline_sequence")
    private Integer bomline_sequence;

    @Column(name = "bomline_routing_id")
    private Integer bomline_routingId;

    @Column(name = "bomline_operation_id")
    private Integer bomline_operationId;

    @Column(name = "bomline_create_uid")
    private Integer bomline_createUid;

    @Column(name = "bomline_create_date")
    private LocalDateTime bomline_createDate;

    @Column(name = "bomline_write_uid")
    private Integer bomline_writeUid;

    @Column(name = "bomline_write_date")
    private LocalDateTime bomline_writeDate;

    public BillOfMaterial() {
    }

    public Integer getColumn_id() {
        return column_id;
    }

    public void setColumn_id(Integer column_id) {
        this.column_id = column_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_defaultCode() {
        return product_defaultCode;
    }

    public void setProduct_defaultCode(String product_defaultCode) {
        this.product_defaultCode = product_defaultCode;
    }

    public Boolean getProduct_active() {
        return product_active;
    }

    public void setProduct_active(Boolean product_active) {
        this.product_active = product_active;
    }

    public Integer getProduct_productTmplId() {
        return product_productTmplId;
    }

    public void setProduct_productTmplId(Integer product_productTmplId) {
        this.product_productTmplId = product_productTmplId;
    }

    public String getProduct_barcode() {
        return product_barcode;
    }

    public void setProduct_barcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public double getProduct_volume() {
        return product_volume;
    }

    public void setProduct_volume(double product_volume) {
        this.product_volume = product_volume;
    }

    public BigDecimal getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(BigDecimal product_weight) {
        this.product_weight = product_weight;
    }

    public LocalDateTime getProduct_messageLastPost() {
        return product_messageLastPost;
    }

    public void setProduct_messageLastPost(LocalDateTime product_messageLastPost) {
        this.product_messageLastPost = product_messageLastPost;
    }

    public Date getProduct_activityDateDeadline() {
        return product_activityDateDeadline;
    }

    public void setProduct_activityDateDeadline(Date product_activityDateDeadline) {
        this.product_activityDateDeadline = product_activityDateDeadline;
    }

    public Integer getProduct_createUid() {
        return product_createUid;
    }

    public void setProduct_createUid(Integer product_createUid) {
        this.product_createUid = product_createUid;
    }

    public LocalDateTime getProduct_createDate() {
        return product_createDate;
    }

    public void setProduct_createDate(LocalDateTime product_createDate) {
        this.product_createDate = product_createDate;
    }

    public Integer getProduct_writeUid() {
        return product_writeUid;
    }

    public void setProduct_writeUid(Integer product_writeUid) {
        this.product_writeUid = product_writeUid;
    }

    public LocalDateTime getProduct_writeDate() {
        return product_writeDate;
    }

    public void setProduct_writeDate(LocalDateTime product_writeDate) {
        this.product_writeDate = product_writeDate;
    }

    public Integer getBom_id() {
        return bom_id;
    }

    public void setBom_id(Integer bom_id) {
        this.bom_id = bom_id;
    }

    public String getBom_code() {
        return bom_code;
    }

    public void setBom_code(String bom_code) {
        this.bom_code = bom_code;
    }

    public Boolean getBom_active() {
        return bom_active;
    }

    public void setBom_active(Boolean bom_active) {
        this.bom_active = bom_active;
    }

    public String getBom_type() {
        return bom_type;
    }

    public void setBom_type(String bom_type) {
        this.bom_type = bom_type;
    }

    public String getBom_productTmplId() {
        return bom_productTmplId;
    }

    public void setBom_productTmplId(String bom_productTmplId) {
        this.bom_productTmplId = bom_productTmplId;
    }

    public BigDecimal getBom_productQty() {
        return bom_productQty;
    }

    public void setBom_productQty(BigDecimal bom_productQty) {
        this.bom_productQty = bom_productQty;
    }

    public Integer getBom_productUomId() {
        return bom_productUomId;
    }

    public void setBom_productUomId(Integer bom_productUomId) {
        this.bom_productUomId = bom_productUomId;
    }

    public Integer getBom_sequence() {
        return bom_sequence;
    }

    public void setBom_sequence(Integer bom_sequence) {
        this.bom_sequence = bom_sequence;
    }

    public Integer getBom_routingId() {
        return bom_routingId;
    }

    public void setBom_routingId(Integer bom_routingId) {
        this.bom_routingId = bom_routingId;
    }

    public String getBom_readyToProduce() {
        return bom_readyToProduce;
    }

    public void setBom_readyToProduce(String bom_readyToProduce) {
        this.bom_readyToProduce = bom_readyToProduce;
    }

    public Integer getBom_pickingTypeId() {
        return bom_pickingTypeId;
    }

    public void setBom_pickingTypeId(Integer bom_pickingTypeId) {
        this.bom_pickingTypeId = bom_pickingTypeId;
    }

    public Integer getBom_companyId() {
        return bom_companyId;
    }

    public void setBom_companyId(Integer bom_companyId) {
        this.bom_companyId = bom_companyId;
    }

    public LocalDateTime getBom_messageLastPost() {
        return bom_messageLastPost;
    }

    public void setBom_messageLastPost(LocalDateTime bom_messageLastPost) {
        this.bom_messageLastPost = bom_messageLastPost;
    }

    public Integer getBom_createUid() {
        return bom_createUid;
    }

    public void setBom_createUid(Integer bom_createUid) {
        this.bom_createUid = bom_createUid;
    }

    public LocalDateTime getBom_createDate() {
        return bom_createDate;
    }

    public void setBom_createDate(LocalDateTime bom_createDate) {
        this.bom_createDate = bom_createDate;
    }

    public Integer getBom_writeUid() {
        return bom_writeUid;
    }

    public void setBom_writeUid(Integer bom_writeUid) {
        this.bom_writeUid = bom_writeUid;
    }

    public LocalDateTime getBom_writeDate() {
        return bom_writeDate;
    }

    public void setBom_writeDate(LocalDateTime bom_writeDate) {
        this.bom_writeDate = bom_writeDate;
    }

    public Integer getBomline_id() {
        return bomline_id;
    }

    public void setBomline_id(Integer bomline_id) {
        this.bomline_id = bomline_id;
    }

    public BigDecimal getBomline_productQty() {
        return bomline_productQty;
    }

    public void setBomline_productQty(BigDecimal bomline_productQty) {
        this.bomline_productQty = bomline_productQty;
    }

    public Integer getBomline_productUomId() {
        return bomline_productUomId;
    }

    public void setBomline_productUomId(Integer bomline_productUomId) {
        this.bomline_productUomId = bomline_productUomId;
    }

    public Integer getBomline_sequence() {
        return bomline_sequence;
    }

    public void setBomline_sequence(Integer bomline_sequence) {
        this.bomline_sequence = bomline_sequence;
    }

    public Integer getBomline_routingId() {
        return bomline_routingId;
    }

    public void setBomline_routingId(Integer bomline_routingId) {
        this.bomline_routingId = bomline_routingId;
    }

    public Integer getBomline_operationId() {
        return bomline_operationId;
    }

    public void setBomline_operationId(Integer bomline_operationId) {
        this.bomline_operationId = bomline_operationId;
    }

    public Integer getBomline_createUid() {
        return bomline_createUid;
    }

    public void setBomline_createUid(Integer bomline_createUid) {
        this.bomline_createUid = bomline_createUid;
    }

    public LocalDateTime getBomline_createDate() {
        return bomline_createDate;
    }

    public void setBomline_createDate(LocalDateTime bomline_createDate) {
        this.bomline_createDate = bomline_createDate;
    }

    public Integer getBomline_writeUid() {
        return bomline_writeUid;
    }

    public void setBomline_writeUid(Integer bomline_writeUid) {
        this.bomline_writeUid = bomline_writeUid;
    }

    public LocalDateTime getBomline_writeDate() {
        return bomline_writeDate;
    }

    public void setBomline_writeDate(LocalDateTime bomline_writeDate) {
        this.bomline_writeDate = bomline_writeDate;
    }

    @Override
    public String toString() {
        return "BillOfMaterial{" +
                "column_id=" + column_id +
                ", product_id=" + product_id +
                ", product_defaultCode='" + product_defaultCode + '\'' +
                ", product_active=" + product_active +
                ", product_productTmplId=" + product_productTmplId +
                ", product_barcode='" + product_barcode + '\'' +
                ", product_volume=" + product_volume +
                ", product_weight=" + product_weight +
                ", product_messageLastPost=" + product_messageLastPost +
                ", product_activityDateDeadline=" + product_activityDateDeadline +
                ", product_createUid=" + product_createUid +
                ", product_createDate=" + product_createDate +
                ", product_writeUid=" + product_writeUid +
                ", product_writeDate=" + product_writeDate +
                ", bom_id=" + bom_id +
                ", bom_code='" + bom_code + '\'' +
                ", bom_active=" + bom_active +
                ", bom_type='" + bom_type + '\'' +
                ", bom_productTmplId='" + bom_productTmplId + '\'' +
                ", bom_productQty=" + bom_productQty +
                ", bom_productUomId=" + bom_productUomId +
                ", bom_sequence=" + bom_sequence +
                ", bom_routingId=" + bom_routingId +
                ", bom_readyToProduce='" + bom_readyToProduce + '\'' +
                ", bom_pickingTypeId=" + bom_pickingTypeId +
                ", bom_companyId=" + bom_companyId +
                ", bom_messageLastPost=" + bom_messageLastPost +
                ", bom_createUid=" + bom_createUid +
                ", bom_createDate=" + bom_createDate +
                ", bom_writeUid=" + bom_writeUid +
                ", bom_writeDate=" + bom_writeDate +
                ", bomline_id=" + bomline_id +
                ", bomline_productQty=" + bomline_productQty +
                ", bomline_productUomId=" + bomline_productUomId +
                ", bomline_sequence=" + bomline_sequence +
                ", bomline_routingId=" + bomline_routingId +
                ", bomline_operationId=" + bomline_operationId +
                ", bomline_createUid=" + bomline_createUid +
                ", bomline_createDate=" + bomline_createDate +
                ", bomline_writeUid=" + bomline_writeUid +
                ", bomline_writeDate=" + bomline_writeDate +
                '}';
    }
}
