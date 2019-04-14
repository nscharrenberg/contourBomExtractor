package com.nscharrenberg.contour.dtos;

import com.nscharrenberg.contour.domain.Bom;
import com.nscharrenberg.contour.domain.BomLine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ProductDto {
    private Integer id;
    private String defaultCode;
    private Boolean active;
    private Integer productTmplId;
    private String barcode;
    private double volume;
    private BigDecimal weight;
    private LocalDateTime messageLastPost;
    private Date activityDateDeadline;
    private Integer createUid;
    private LocalDateTime createDate;
    private Integer writeUid;
    private LocalDateTime writeDate;

    private List<BomLine> bomLines;
    private List<Bom> boms;

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

    public Boolean getActive() {
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

    public List<BomLine> getBomLines() {
        return bomLines;
    }

    public void setBomLines(List<BomLine> bomLines) {
        this.bomLines = bomLines;
    }

    public List<Bom> getBoms() {
        return boms;
    }

    public void setBoms(List<Bom> boms) {
        this.boms = boms;
    }

    @Override
    public String toString() {
        return String.format("id:%s default_code:%s productTmplId:%s barcode:%s volume:%s weight:%s", id, defaultCode, productTmplId, barcode, volume, weight);
    }
}
