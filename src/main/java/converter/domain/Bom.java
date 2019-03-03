package converter.domain;

import java.util.HashSet;
import java.util.Set;

public class Bom {
    private int id;
    private String code;
    private boolean active;
    private String type;
    private int productTmplId;
    private Product product;
    private double productQty;
    private int productUomId;
    private int sequence;
    private int routingId;
    private String readyToProduce;
    private int pickingtypeId;
    private int companyId;
    private String messageLastPost;
    private int createDid;
    private String createDate;
    private int writeUid;
    private String writeDate;

    private Set<BomLine> bomLines;

    public Bom(int id, String code, boolean active, String type, int productTmplId, Product product, double productQty, int productUomId, int sequence, int routingId, String readyToProduce, int pickingtypeId, int companyId, String messageLastPost, int createDid, String createDate, int writeUid, String writeDate) {
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
        this.bomLines = new HashSet<BomLine>();
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

    public int getProductTmplId() {
        return productTmplId;
    }

    public void setProductTmplId(int productTmplId) {
        this.productTmplId = productTmplId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getProductQty() {
        return productQty;
    }

    public void setProductQty(double productQty) {
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

    public int getPickingtypeId() {
        return pickingtypeId;
    }

    public void setPickingtypeId(int pickingtypeId) {
        this.pickingtypeId = pickingtypeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getMessageLastPost() {
        return messageLastPost;
    }

    public void setMessageLastPost(String messageLastPost) {
        this.messageLastPost = messageLastPost;
    }

    public int getCreateDid() {
        return createDid;
    }

    public void setCreateDid(int createDid) {
        this.createDid = createDid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getWriteUid() {
        return writeUid;
    }

    public void setWriteUid(int writeUid) {
        this.writeUid = writeUid;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public Set<BomLine> getBomLines() {
        return bomLines;
    }

    public void setBomLines(Set<BomLine> bomLines) {
        this.bomLines = bomLines;
    }
}
