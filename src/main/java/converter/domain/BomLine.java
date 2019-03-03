package converter.domain;

public class BomLine {
    private int id;
    private Product product;
    private double productQty;
    private int productUomId;
    private int sequence;
    private int routingId;
    private Bom bom;
    private int operationId;
    private int createUid;
    private String createDate;
    private int writeUid;
    private String writeDate;

    public BomLine(int id, Product product, double productQty, int productUomId, int sequence, int routingId, Bom bom, int operationId, int createUid, String createDate, int writeUid, String writeDate) {
        this.id = id;
        this.product = product;
        this.productQty = productQty;
        this.productUomId = productUomId;
        this.sequence = sequence;
        this.routingId = routingId;
        this.bom = bom;
        this.operationId = operationId;
        this.createUid = createUid;
        this.createDate = createDate;
        this.writeUid = writeUid;
        this.writeDate = writeDate;
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
}
