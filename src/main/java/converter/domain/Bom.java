package converter.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bom {
    private Integer id;
    private String code;
    private Boolean active;
    private String type;
    private Integer productTmplId;
    private Product product;
    private Double productQty;
    private Integer productUomId;
    private Integer sequence;
    private Integer routingId;
    private String readyToProduce;
    private Integer pickingtypeId;
    private Integer companyId;
    private String messageLastPost;
    private Integer createDid;
    private String createDate;
    private Integer writeUid;
    private String writeDate;

    private List<BomLine> bomLines;

    public Bom(Integer id, String code, Boolean active, String type, Integer productTmplId, Product product, Double productQty, Integer productUomId, Integer sequence, Integer routingId, String readyToProduce, Integer pickingtypeId, Integer companyId, String messageLastPost, Integer createDid, String createDate, Integer writeUid, String writeDate) {
        this.id = id;
        this.code = code;
        this.active = active;
        this.type = type;
        this.productTmplId = productTmplId;
        this.product = product;
        this.productQty = productQty;
        this.productUomId = productUomId;
        this.sequence = sequence;
        this.routingId = routingId;
        this.readyToProduce = readyToProduce;
        this.pickingtypeId = pickingtypeId;
        this.companyId = companyId;
        this.messageLastPost = messageLastPost;
        this.createDid = createDid;
        this.createDate = createDate;
        this.writeUid = writeUid;
        this.writeDate = writeDate;
        this.bomLines = new ArrayList<BomLine>();
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

    public Boolean getActive() {
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

    public Integer getProductTmplId() {
        return productTmplId;
    }

    public void setProductTmplId(Integer productTmplId) {
        this.productTmplId = productTmplId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getProductQty() {
        return productQty;
    }

    public void setProductQty(Double productQty) {
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

    public Integer getPickingtypeId() {
        return pickingtypeId;
    }

    public void setPickingtypeId(Integer pickingtypeId) {
        this.pickingtypeId = pickingtypeId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getMessageLastPost() {
        return messageLastPost;
    }

    public void setMessageLastPost(String messageLastPost) {
        this.messageLastPost = messageLastPost;
    }

    public Integer getCreateDid() {
        return createDid;
    }

    public void setCreateDid(Integer createDid) {
        this.createDid = createDid;
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

    public List<BomLine> getBomLines() {
        return bomLines;
    }

    public void setBomLines(List<BomLine> bomLines) {
        this.bomLines = bomLines;
    }

    public void addBomLine(BomLine bomLine) {
        this.bomLines.add(bomLine);
    }

    public void removeBomLine(BomLine bomLine) {
        this.bomLines.remove(bomLine);
    }
}
