package com.nscharrenberg.contour.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_template")
@NamedQueries({
        @NamedQuery(name = "template.findAll", query = "SELECT p FROM Template p ORDER BY p.id ASC"),
        @NamedQuery(name = "template.findById", query = "SELECT p FROM Template p WHERE p.id = :id ORDER BY p.id ASC"),
        @NamedQuery(name = "template.findByDefaultCode", query = "SELECT p FROM Template p WHERE p.defaultCode = :id ORDER BY p.defaultCode ASC")
})
public class Template {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "sequence")
    private Integer sequence;

    @Column(name = "description")
    private String description;

    @Column(name = "description_purchase")
    private String descriptionPurchase;

    @Column(name = "description_sale")
    private String descriptionSale;

    @Column(name = "type")
    private String type;

    @Column(name = "rental")
    private Boolean rental;

    @Column(name = "categ_id")
    private Integer categ_id;

    @Column(name = "list_price")
    private BigDecimal listPrice;

    @Column(name = "volume")
    private Double volume;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "sale_ok")
    private Boolean saleOk;

    @Column(name = "purchase_ok")
    private Boolean purchaseOk;

    @Column(name = "uom_id")
    private Integer uomId;

    @Column(name = "uom_po_id")
    private Integer uomPoId;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "color")
    private Integer color;

    @Column(name = "default_code")
    private String defaultCode;

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

    @Column(name = "responsible_id")
    private Integer responsibleId;

    @Column(name = "sale_delay")
    private Double saleDelay;

    @Column(name = "tracking")
    private String tracking;

    @Column(name = "description_picking")
    private String descriptionPicking;

    @Column(name = "description_pickingout")
    private String descriptionPickingOut;

    @Column(name = "description_pickingin")
    private String descriptionPickingIn;

    @Column(name = "purchase_method")
    private String purchaseMethod;

    @Column(name = "purchase_line_warn")
    private String purchaseLineWarn;

    @Column(name = "purchase_line_warn_msg")
    private String purchaseLineWarnMsg;

    @Column(name = "produce_delay")
    private Double produceDelay;

    @Column(name = "can_be_expensed")
    private Boolean canBeExpensed;

    @Column(name = "landed_cost_ok")
    private Boolean landedCostOk;

    @Column(name = "split_method")
    private String splitMethod;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "sale_line_warn")
    private String saleLineWarn;

    @Column(name = "sale_line_warn_msg")
    private String saleLineWarnMsg;

    @Column(name = "expense_policy")
    private String expensePolicy;

    @Column(name = "invoice_policy")
    private String invoicePolicy;

    @Column(name = "service_tracking")
    private String serviceTracking;

    @OneToMany(mappedBy = "productTmplId")
    private List<Bom> boms;

    @OneToMany(mappedBy = "productTmplId")
    private List<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionPurchase() {
        return descriptionPurchase;
    }

    public void setDescriptionPurchase(String descriptionPurchase) {
        this.descriptionPurchase = descriptionPurchase;
    }

    public String getDescriptionSale() {
        return descriptionSale;
    }

    public void setDescriptionSale(String descriptionSale) {
        this.descriptionSale = descriptionSale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRental() {
        return rental;
    }

    public void setRental(Boolean rental) {
        this.rental = rental;
    }

    public Integer getCateg_id() {
        return categ_id;
    }

    public void setCateg_id(Integer categ_id) {
        this.categ_id = categ_id;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Boolean getSaleOk() {
        return saleOk;
    }

    public void setSaleOk(Boolean saleOk) {
        this.saleOk = saleOk;
    }

    public Boolean getPurchaseOk() {
        return purchaseOk;
    }

    public void setPurchaseOk(Boolean purchaseOk) {
        this.purchaseOk = purchaseOk;
    }

    public Integer getUomId() {
        return uomId;
    }

    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }

    public Integer getUomPoId() {
        return uomPoId;
    }

    public void setUomPoId(Integer uomPoId) {
        this.uomPoId = uomPoId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getDefaultCode() {
        return defaultCode;
    }

    public void setDefaultCode(String defaultCode) {
        this.defaultCode = defaultCode;
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

    public Integer getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Integer responsibleId) {
        this.responsibleId = responsibleId;
    }

    public Double getSaleDelay() {
        return saleDelay;
    }

    public void setSaleDelay(Double saleDelay) {
        this.saleDelay = saleDelay;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getDescriptionPicking() {
        return descriptionPicking;
    }

    public void setDescriptionPicking(String descriptionPicking) {
        this.descriptionPicking = descriptionPicking;
    }

    public String getDescriptionPickingOut() {
        return descriptionPickingOut;
    }

    public void setDescriptionPickingOut(String descriptionPickingOut) {
        this.descriptionPickingOut = descriptionPickingOut;
    }

    public String getDescriptionPickingIn() {
        return descriptionPickingIn;
    }

    public void setDescriptionPickingIn(String descriptionPickingIn) {
        this.descriptionPickingIn = descriptionPickingIn;
    }

    public String getPurchaseMethod() {
        return purchaseMethod;
    }

    public void setPurchaseMethod(String purchaseMethod) {
        this.purchaseMethod = purchaseMethod;
    }

    public String getPurchaseLineWarn() {
        return purchaseLineWarn;
    }

    public void setPurchaseLineWarn(String purchaseLineWarn) {
        this.purchaseLineWarn = purchaseLineWarn;
    }

    public String getPurchaseLineWarnMsg() {
        return purchaseLineWarnMsg;
    }

    public void setPurchaseLineWarnMsg(String purchaseLineWarnMsg) {
        this.purchaseLineWarnMsg = purchaseLineWarnMsg;
    }

    public Double getProduceDelay() {
        return produceDelay;
    }

    public void setProduceDelay(Double productDelay) {
        this.produceDelay = productDelay;
    }

    public Boolean getCanBeExpensed() {
        return canBeExpensed;
    }

    public void setCanBeExpensed(Boolean canBeExpensed) {
        this.canBeExpensed = canBeExpensed;
    }

    public Boolean getLandedCostOk() {
        return landedCostOk;
    }

    public void setLandedCostOk(Boolean landedCostOk) {
        this.landedCostOk = landedCostOk;
    }

    public String getSplitMethod() {
        return splitMethod;
    }

    public void setSplitMethod(String splitMethod) {
        this.splitMethod = splitMethod;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSaleLineWarn() {
        return saleLineWarn;
    }

    public void setSaleLineWarn(String saleLineWarn) {
        this.saleLineWarn = saleLineWarn;
    }

    public String getSaleLineWarnMsg() {
        return saleLineWarnMsg;
    }

    public void setSaleLineWarnMsg(String saleLineWarnMsg) {
        this.saleLineWarnMsg = saleLineWarnMsg;
    }

    public String getExpensePolicy() {
        return expensePolicy;
    }

    public void setExpensePolicy(String expensePolicy) {
        this.expensePolicy = expensePolicy;
    }

    public String getInvoicePolicy() {
        return invoicePolicy;
    }

    public void setInvoicePolicy(String invoicePolicy) {
        this.invoicePolicy = invoicePolicy;
    }

    public String getServiceTracking() {
        return serviceTracking;
    }

    public void setServiceTracking(String serviceTracking) {
        this.serviceTracking = serviceTracking;
    }

    public List<Bom> getBoms() {
        return boms;
    }

    public void setBoms(List<Bom> boms) {
        this.boms = boms;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", defaultCode='" + defaultCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return Objects.equals(id, template.id) &&
                Objects.equals(name, template.name) &&
                Objects.equals(sequence, template.sequence) &&
                Objects.equals(description, template.description) &&
                Objects.equals(descriptionPurchase, template.descriptionPurchase) &&
                Objects.equals(descriptionSale, template.descriptionSale) &&
                Objects.equals(type, template.type) &&
                Objects.equals(rental, template.rental) &&
                Objects.equals(categ_id, template.categ_id) &&
                Objects.equals(listPrice, template.listPrice) &&
                Objects.equals(volume, template.volume) &&
                Objects.equals(weight, template.weight) &&
                Objects.equals(saleOk, template.saleOk) &&
                Objects.equals(purchaseOk, template.purchaseOk) &&
                Objects.equals(uomId, template.uomId) &&
                Objects.equals(uomPoId, template.uomPoId) &&
                Objects.equals(companyId, template.companyId) &&
                Objects.equals(active, template.active) &&
                Objects.equals(color, template.color) &&
                Objects.equals(defaultCode, template.defaultCode) &&
                Objects.equals(messageLastPost, template.messageLastPost) &&
                Objects.equals(activityDateDeadline, template.activityDateDeadline) &&
                Objects.equals(createUid, template.createUid) &&
                Objects.equals(createDate, template.createDate) &&
                Objects.equals(writeUid, template.writeUid) &&
                Objects.equals(writeDate, template.writeDate) &&
                Objects.equals(responsibleId, template.responsibleId) &&
                Objects.equals(saleDelay, template.saleDelay) &&
                Objects.equals(tracking, template.tracking) &&
                Objects.equals(descriptionPicking, template.descriptionPicking) &&
                Objects.equals(descriptionPickingOut, template.descriptionPickingOut) &&
                Objects.equals(descriptionPickingIn, template.descriptionPickingIn) &&
                Objects.equals(purchaseMethod, template.purchaseMethod) &&
                Objects.equals(purchaseLineWarn, template.purchaseLineWarn) &&
                Objects.equals(purchaseLineWarnMsg, template.purchaseLineWarnMsg) &&
                Objects.equals(produceDelay, template.produceDelay) &&
                Objects.equals(canBeExpensed, template.canBeExpensed) &&
                Objects.equals(landedCostOk, template.landedCostOk) &&
                Objects.equals(splitMethod, template.splitMethod) &&
                Objects.equals(serviceType, template.serviceType) &&
                Objects.equals(saleLineWarn, template.saleLineWarn) &&
                Objects.equals(saleLineWarnMsg, template.saleLineWarnMsg) &&
                Objects.equals(expensePolicy, template.expensePolicy) &&
                Objects.equals(invoicePolicy, template.invoicePolicy) &&
                Objects.equals(serviceTracking, template.serviceTracking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sequence, description, descriptionPurchase, descriptionSale, type, rental, categ_id, listPrice, volume, weight, saleOk, purchaseOk, uomId, uomPoId, companyId, active, color, defaultCode, messageLastPost, activityDateDeadline, createUid, createDate, writeUid, writeDate, responsibleId, saleDelay, tracking, descriptionPicking, descriptionPickingOut, descriptionPickingIn, purchaseMethod, purchaseLineWarn, purchaseLineWarnMsg, produceDelay, canBeExpensed, landedCostOk, splitMethod, serviceType, saleLineWarn, saleLineWarnMsg, expensePolicy, invoicePolicy, serviceTracking);
    }
}
