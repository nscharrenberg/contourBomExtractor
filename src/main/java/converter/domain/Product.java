package converter.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Product {
    private Integer id = null;
    private String defaultCode = null;
    private Boolean active = null;
    private Integer productTmplId = null;
    private String barcode = null;
    private Double volume = null;
    private Double weight = null;
    private String messageLastPost = null;
    private String activityDateDeadline = null;
    private Integer createUid = null;
    private String createDate = null;
    private Integer writeUid = null;
    private String writeDate = null;

    private List<Bom> boms;
    private List<BomLine> bomLines;

    public Product(Integer id, String defaultCode, Boolean active, Integer productTmplId, String barcode, Double volume, Double weight, String messageLastPost, String activityDateDeadline, Integer createUid, String createDate, Integer writeUid, String writeDate) {
        this.id = id;
        this.defaultCode = defaultCode;
        this.active = active;
        this.productTmplId = productTmplId;
        this.barcode = barcode;
        this.volume = volume;
        this.weight = weight;
        this.messageLastPost = messageLastPost;
        this.activityDateDeadline = activityDateDeadline;
        this.createUid = createUid;
        this.createDate = createDate;
        this.writeUid = writeUid;
        this.writeDate = writeDate;
        this.boms = new ArrayList<>();
        this.bomLines = new ArrayList<BomLine>();
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

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getMessageLastPost() {
        return messageLastPost;
    }

    public void setMessageLastPost(String messageLastPost) {
        this.messageLastPost = messageLastPost;
    }

    public String getActivityDateDeadline() {
        return activityDateDeadline;
    }

    public void setActivityDateDeadline(String activityDateDeadline) {
        this.activityDateDeadline = activityDateDeadline;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getWriteUid() {
        return writeUid;
    }

    public void setWriteUid(Integer writeUid) {
        this.writeUid = writeUid;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public List<Bom> getBoms() {
        return boms;
    }

    public void setBoms(List<Bom> boms) {
        this.boms = boms;
    }

    public List<BomLine> getBomLines() {
        return bomLines;
    }

    public void setBomLines(List<BomLine> bomLines) {
        this.bomLines = bomLines;
    }

    public void addBom(Bom bom) {
        this.boms.add(bom);
    }

    public void removeBom(Bom bom) {
        this.boms.remove(bom);
    }

    public void addBomLine(BomLine bomLine) {
        this.bomLines.add(bomLine);
    }

    public void removeBomLine(BomLine bomLine) {
        this.bomLines.remove(bomLine);
    }
}
