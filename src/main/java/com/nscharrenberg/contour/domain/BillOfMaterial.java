package com.nscharrenberg.contour.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "stuklijst")
public class BillOfMaterial {
    @Id
    @SequenceGenerator(name="stuklijst_id_seq",
            sequenceName="stuklijst_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="stuklijst_id_seq")
    @Column(name = "stuklijst_id", updatable=false)
    private Integer stuklijst_id;

    /**
     * Template Properties
     */
    @Column(name = "template_id")
    private Integer template_id;

    @Column(name = "template_name")
    private String template_name;

    @Column(name = "template_sequence")
    private Integer template_sequence;

    @Column(name = "template_description")
    private String template_description;

    @Column(name = "template_description_purchase")
    private String template_descriptionPurchase;

    @Column(name = "template_description_sale")
    private String template_descriptionSale;

    @Column(name = "template_type")
    private String template_type;

    @Column(name = "template_rental")
    private Boolean template_rental;

    @Column(name = "template_categ_id")
    private Integer template_categ_id;

    @Column(name = "template_list_price")
    private BigDecimal template_listPrice;

    @Column(name = "template_volume")
    private Double template_volume;

    @Column(name = "template_weight")
    private BigDecimal template_weight;

    @Column(name = "template_sale_ok")
    private Boolean template_saleOk;

    @Column(name = "template_purchase_ok")
    private Boolean template_purchaseOk;

    @Column(name = "template_uom_id")
    private Integer template_uomId;

    @Column(name = "template_uom_po_id")
    private Integer template_uomPoId;

    @Column(name = "template_company_id")
    private Integer template_companyId;

    @Column(name = "template_active")
    private Boolean template_active;

    @Column(name = "template_color")
    private Integer template_color;

    @Column(name = "template_default_code")
    private String template_defaultCode;

    @Column(name = "template_message_last_post")
    private LocalDateTime template_messageLastPost;

    @Column(name = "template_activity_date_deadline")
    private Date template_activityDateDeadline;

    @Column(name = "template_create_uid")
    private Integer template_createUid;

    @Column(name = "template_create_date")
    private LocalDateTime template_createDate;

    @Column(name = "template_write_uid")
    private Integer template_writeUid;

    @Column(name = "template_write_date")
    private LocalDateTime template_writeDate;

    @Column(name = "template_responsible_id")
    private Integer template_responsibleId;

    @Column(name = "template_sale_delay")
    private Double template_saleDelay;

    @Column(name = "template_tracking")
    private String template_tracking;

    @Column(name = "template_description_picking")
    private String template_descriptionPicking;

    @Column(name = "template_description_pickingout")
    private String template_descriptionPickingOut;

    @Column(name = "template_description_pickingin")
    private String template_descriptionPickingIn;

    @Column(name = "template_purchase_method")
    private String template_purchaseMethod;

    @Column(name = "template_purchase_line_warn")
    private String template_purchaseLineWarn;

    @Column(name = "template_purchase_line_warn_msg")
    private String template_purchaseLineWarnMsg;

    @Column(name = "template_produce_delay")
    private Double template_produceDelay;

    @Column(name = "template_can_be_expensed")
    private Boolean template_canBeExpensed;

    @Column(name = "template_landed_cost_ok")
    private Boolean template_landedCostOk;

    @Column(name = "template_split_method")
    private String template_splitMethod;

    @Column(name = "template_service_type")
    private String template_serviceType;

    @Column(name = "template_sale_line_warn")
    private String template_saleLineWarn;

    @Column(name = "template_sale_line_warn_msg")
    private String template_saleLineWarnMsg;

    @Column(name = "template_expense_policy")
    private String template_expensePolicy;

    @Column(name = "template_invoice_policy")
    private String template_invoicePolicy;

    @Column(name = "template_service_tracking")
    private String template_serviceTracking;


    /**
     * Product Properties
     */
    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "product_default_code")
    private String product_defaultCode;

    @Column(name = "product_active")
    private Boolean product_active;

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

    public Integer getStuklijst_id() {
        return stuklijst_id;
    }

    public void setStuklijst_id(Integer column_id) {
        this.stuklijst_id = column_id;
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

    public Integer getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public Integer getTemplate_sequence() {
        return template_sequence;
    }

    public void setTemplate_sequence(Integer template_sequence) {
        this.template_sequence = template_sequence;
    }

    public String getTemplate_description() {
        return template_description;
    }

    public void setTemplate_description(String template_description) {
        this.template_description = template_description;
    }

    public String getTemplate_descriptionPurchase() {
        return template_descriptionPurchase;
    }

    public void setTemplate_descriptionPurchase(String template_descriptionPurchase) {
        this.template_descriptionPurchase = template_descriptionPurchase;
    }

    public String getTemplate_descriptionSale() {
        return template_descriptionSale;
    }

    public void setTemplate_descriptionSale(String template_descriptionSale) {
        this.template_descriptionSale = template_descriptionSale;
    }

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public Boolean getTemplate_rental() {
        return template_rental;
    }

    public void setTemplate_rental(Boolean template_rental) {
        this.template_rental = template_rental;
    }

    public Integer getTemplate_categ_id() {
        return template_categ_id;
    }

    public void setTemplate_categ_id(Integer template_categ_id) {
        this.template_categ_id = template_categ_id;
    }

    public BigDecimal getTemplate_listPrice() {
        return template_listPrice;
    }

    public void setTemplate_listPrice(BigDecimal template_listPrice) {
        this.template_listPrice = template_listPrice;
    }

    public Double getTemplate_volume() {
        return template_volume;
    }

    public void setTemplate_volume(Double template_volume) {
        this.template_volume = template_volume;
    }

    public BigDecimal getTemplate_weight() {
        return template_weight;
    }

    public void setTemplate_weight(BigDecimal template_weight) {
        this.template_weight = template_weight;
    }

    public Boolean getTemplate_saleOk() {
        return template_saleOk;
    }

    public void setTemplate_saleOk(Boolean template_saleOk) {
        this.template_saleOk = template_saleOk;
    }

    public Boolean getTemplate_purchaseOk() {
        return template_purchaseOk;
    }

    public void setTemplate_purchaseOk(Boolean template_purchaseOk) {
        this.template_purchaseOk = template_purchaseOk;
    }

    public Integer getTemplate_uomId() {
        return template_uomId;
    }

    public void setTemplate_uomId(Integer template_uomId) {
        this.template_uomId = template_uomId;
    }

    public Integer getTemplate_uomPoId() {
        return template_uomPoId;
    }

    public void setTemplate_uomPoId(Integer template_uomPoId) {
        this.template_uomPoId = template_uomPoId;
    }

    public Integer getTemplate_companyId() {
        return template_companyId;
    }

    public void setTemplate_companyId(Integer template_companyId) {
        this.template_companyId = template_companyId;
    }

    public Boolean getTemplate_active() {
        return template_active;
    }

    public void setTemplate_active(Boolean template_active) {
        this.template_active = template_active;
    }

    public Integer getTemplate_color() {
        return template_color;
    }

    public void setTemplate_color(Integer template_color) {
        this.template_color = template_color;
    }

    public String getTemplate_defaultCode() {
        return template_defaultCode;
    }

    public void setTemplate_defaultCode(String template_defaultCode) {
        this.template_defaultCode = template_defaultCode;
    }

    public LocalDateTime getTemplate_messageLastPost() {
        return template_messageLastPost;
    }

    public void setTemplate_messageLastPost(LocalDateTime template_messageLastPost) {
        this.template_messageLastPost = template_messageLastPost;
    }

    public Date getTemplate_activityDateDeadline() {
        return template_activityDateDeadline;
    }

    public void setTemplate_activityDateDeadline(Date template_activityDateDeadline) {
        this.template_activityDateDeadline = template_activityDateDeadline;
    }

    public Integer getTemplate_createUid() {
        return template_createUid;
    }

    public void setTemplate_createUid(Integer template_createUid) {
        this.template_createUid = template_createUid;
    }

    public LocalDateTime getTemplate_createDate() {
        return template_createDate;
    }

    public void setTemplate_createDate(LocalDateTime template_createDate) {
        this.template_createDate = template_createDate;
    }

    public Integer getTemplate_writeUid() {
        return template_writeUid;
    }

    public void setTemplate_writeUid(Integer template_writeUid) {
        this.template_writeUid = template_writeUid;
    }

    public LocalDateTime getTemplate_writeDate() {
        return template_writeDate;
    }

    public void setTemplate_writeDate(LocalDateTime template_writeDate) {
        this.template_writeDate = template_writeDate;
    }

    public Integer getTemplate_responsibleId() {
        return template_responsibleId;
    }

    public void setTemplate_responsibleId(Integer template_responsibleId) {
        this.template_responsibleId = template_responsibleId;
    }

    public Double getTemplate_saleDelay() {
        return template_saleDelay;
    }

    public void setTemplate_saleDelay(Double template_saleDelay) {
        this.template_saleDelay = template_saleDelay;
    }

    public String getTemplate_tracking() {
        return template_tracking;
    }

    public void setTemplate_tracking(String template_tracking) {
        this.template_tracking = template_tracking;
    }

    public String getTemplate_descriptionPicking() {
        return template_descriptionPicking;
    }

    public void setTemplate_descriptionPicking(String template_descriptionPicking) {
        this.template_descriptionPicking = template_descriptionPicking;
    }

    public String getTemplate_descriptionPickingOut() {
        return template_descriptionPickingOut;
    }

    public void setTemplate_descriptionPickingOut(String template_descriptionPickingOut) {
        this.template_descriptionPickingOut = template_descriptionPickingOut;
    }

    public String getTemplate_descriptionPickingIn() {
        return template_descriptionPickingIn;
    }

    public void setTemplate_descriptionPickingIn(String template_descriptionPickingIn) {
        this.template_descriptionPickingIn = template_descriptionPickingIn;
    }

    public String getTemplate_purchaseMethod() {
        return template_purchaseMethod;
    }

    public void setTemplate_purchaseMethod(String template_purchaseMethod) {
        this.template_purchaseMethod = template_purchaseMethod;
    }

    public String getTemplate_purchaseLineWarn() {
        return template_purchaseLineWarn;
    }

    public void setTemplate_purchaseLineWarn(String template_purchaseLineWarn) {
        this.template_purchaseLineWarn = template_purchaseLineWarn;
    }

    public String getTemplate_purchaseLineWarnMsg() {
        return template_purchaseLineWarnMsg;
    }

    public void setTemplate_purchaseLineWarnMsg(String template_purchaseLineWarnMsg) {
        this.template_purchaseLineWarnMsg = template_purchaseLineWarnMsg;
    }

    public Double getTemplate_produceDelay() {
        return template_produceDelay;
    }

    public void setTemplate_produceDelay(Double template_produceDelay) {
        this.template_produceDelay = template_produceDelay;
    }

    public Boolean getTemplate_canBeExpensed() {
        return template_canBeExpensed;
    }

    public void setTemplate_canBeExpensed(Boolean template_canBeExpensed) {
        this.template_canBeExpensed = template_canBeExpensed;
    }

    public Boolean getTemplate_landedCostOk() {
        return template_landedCostOk;
    }

    public void setTemplate_landedCostOk(Boolean template_landedCostOk) {
        this.template_landedCostOk = template_landedCostOk;
    }

    public String getTemplate_splitMethod() {
        return template_splitMethod;
    }

    public void setTemplate_splitMethod(String template_splitMethod) {
        this.template_splitMethod = template_splitMethod;
    }

    public String getTemplate_serviceType() {
        return template_serviceType;
    }

    public void setTemplate_serviceType(String template_serviceType) {
        this.template_serviceType = template_serviceType;
    }

    public String getTemplate_saleLineWarn() {
        return template_saleLineWarn;
    }

    public void setTemplate_saleLineWarn(String template_saleLineWarn) {
        this.template_saleLineWarn = template_saleLineWarn;
    }

    public String getTemplate_saleLineWarnMsg() {
        return template_saleLineWarnMsg;
    }

    public void setTemplate_saleLineWarnMsg(String template_saleLineWarnMsg) {
        this.template_saleLineWarnMsg = template_saleLineWarnMsg;
    }

    public String getTemplate_expensePolicy() {
        return template_expensePolicy;
    }

    public void setTemplate_expensePolicy(String template_expensePolicy) {
        this.template_expensePolicy = template_expensePolicy;
    }

    public String getTemplate_invoicePolicy() {
        return template_invoicePolicy;
    }

    public void setTemplate_invoicePolicy(String template_invoicePolicy) {
        this.template_invoicePolicy = template_invoicePolicy;
    }

    public String getTemplate_serviceTracking() {
        return template_serviceTracking;
    }

    public void setTemplate_serviceTracking(String template_serviceTracking) {
        this.template_serviceTracking = template_serviceTracking;
    }

    @Override
    public String toString() {
        return "BillOfMaterial{" +
                "column_id=" + stuklijst_id +
                ", template_id=" + template_id +
                ", template_name='" + template_name + '\'' +
                ", template_sequence=" + template_sequence +
                ", template_description='" + template_description + '\'' +
                ", template_descriptionPurchase='" + template_descriptionPurchase + '\'' +
                ", template_descriptionSale='" + template_descriptionSale + '\'' +
                ", template_type='" + template_type + '\'' +
                ", template_rental=" + template_rental +
                ", template_categ_id=" + template_categ_id +
                ", template_listPrice=" + template_listPrice +
                ", template_volume=" + template_volume +
                ", template_weight=" + template_weight +
                ", template_saleOk=" + template_saleOk +
                ", template_purchaseOk=" + template_purchaseOk +
                ", template_uomId=" + template_uomId +
                ", template_uomPoId=" + template_uomPoId +
                ", template_companyId=" + template_companyId +
                ", template_active=" + template_active +
                ", template_color=" + template_color +
                ", template_defaultCode='" + template_defaultCode + '\'' +
                ", template_messageLastPost=" + template_messageLastPost +
                ", template_activityDateDeadline=" + template_activityDateDeadline +
                ", template_createUid=" + template_createUid +
                ", template_createDate=" + template_createDate +
                ", template_writeUid=" + template_writeUid +
                ", template_writeDate=" + template_writeDate +
                ", template_responsibleId=" + template_responsibleId +
                ", template_saleDelay=" + template_saleDelay +
                ", template_tracking='" + template_tracking + '\'' +
                ", template_descriptionPicking='" + template_descriptionPicking + '\'' +
                ", template_descriptionPickingOut='" + template_descriptionPickingOut + '\'' +
                ", template_descriptionPickingIn='" + template_descriptionPickingIn + '\'' +
                ", template_purchaseMethod='" + template_purchaseMethod + '\'' +
                ", template_purchaseLineWarn='" + template_purchaseLineWarn + '\'' +
                ", template_purchaseLineWarnMsg='" + template_purchaseLineWarnMsg + '\'' +
                ", template_produceDelay=" + template_produceDelay +
                ", template_canBeExpensed=" + template_canBeExpensed +
                ", template_landedCostOk=" + template_landedCostOk +
                ", template_splitMethod='" + template_splitMethod + '\'' +
                ", template_serviceType='" + template_serviceType + '\'' +
                ", template_saleLineWarn='" + template_saleLineWarn + '\'' +
                ", template_saleLineWarnMsg='" + template_saleLineWarnMsg + '\'' +
                ", template_expensePolicy='" + template_expensePolicy + '\'' +
                ", template_invoicePolicy='" + template_invoicePolicy + '\'' +
                ", template_serviceTracking='" + template_serviceTracking + '\'' +
                ", product_id=" + product_id +
                ", product_defaultCode='" + product_defaultCode + '\'' +
                ", product_active=" + product_active +
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
