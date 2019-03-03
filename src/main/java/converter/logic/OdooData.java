package converter.logic;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import converter.domain.Bom;
import converter.domain.BomLine;
import converter.domain.Product;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OdooData {
    private Connection connection;
    private List<Product> productList = new ArrayList<>();
    private List<Bom> bomList = new ArrayList<>();
    private List<BomLine> bomLineList = new ArrayList<>();

    public OdooData(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getProducts() throws Exception {
        String query = "SELECT * FROM product_product AS p ORDER BY p.id";

        if(connection == null) {
            throw new Exception("No database connection found");
        }

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<Product> products = new ArrayList<Product>();

        while (rs.next()) {
            //Iterate Row
            Product product = null;
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("default_code"),
                        rs.getBoolean("active"),
                        rs.getInt("product_tmpl_id"),
                        rs.getString("barcode"),
                        rs.getDouble("volume"),
                        rs.getDouble("weight"),
                        rs.getString("message_last_post"),
                        rs.getString("activity_date_deadline"),
                        rs.getInt("create_uid"),
                        rs.getString("create_date"),
                        rs.getInt("write_uid"),
                        rs.getString("write_date"));
            }

            products.add(product);
        }

        return products;
    }

    public List<Product> getBomItems() throws Exception {
        String query = "SELECT * FROM mrp_bom AS b ORDER BY b.id";

        List<Product> products = getProducts();

        if(connection == null) {
            throw new Exception("No database connection found");
        }

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            //Iterate Row
            Bom bom = null;
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                final Integer pid = rs.getInt("product_id");
                Product product = Iterables.tryFind(products, p -> pid.equals(p.getId())).orNull();

                bom = new Bom(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getBoolean("active"),
                        rs.getString("type"),
                        rs.getInt("product_tmpl_id"),
                        product,
                        rs.getDouble("product_qty"),
                        rs.getInt("product_uom_id"),
                        rs.getInt("sequence"),
                        rs.getInt("routing_id"),
                        rs.getString("ready_to_produce"),
                        rs.getInt("picking_type_id"),
                        rs.getInt("company_id"),
                        rs.getString("message_last_post"),
                        rs.getInt("create_uid"),
                        rs.getString("create_date"),
                        rs.getInt("write_uid"),
                        rs.getString("write_date")
                        );
            }

            if (bom != null && bom.getProduct() != null) {
                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);
                    if (p.getId() == bom.getProduct().getId()) {
                        p.addBom(bom);
                        bomList.add(bom);
                    }
                }
            }
        }

        this.productList = products;
        return products;
    }

    public List<Product> getBomLines() throws Exception {
        String query = "SELECT * FROM mrp_bom_line AS b ORDER BY b.id";

        List<Product> products = getBomItems();

        if(connection == null) {
            throw new Exception("No database connection found");
        }

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            //Iterate Row
            BomLine bomLine = null;
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                final Integer pid = rs.getInt("product_id");
                final Integer bid = rs.getInt("bom_id");
                Product product = Iterables.tryFind(products, p -> pid.equals(p.getId())).orNull();
                Bom bom = Iterables.tryFind(product.getBoms(), b -> bid.equals(b.getId())).orNull();

                bomLine = new BomLine(
                        rs.getInt("id"),
                        product,
                        rs.getDouble("product_qty"),
                        rs.getInt("product_uom_id"),
                        rs.getInt("sequence"),
                        rs.getInt("routing_id"),
                        bom,
                        rs.getInt("operation_id"),
                        rs.getInt("create_uid"),
                        rs.getString("create_date"),
                        rs.getInt("write_uid"),
                        rs.getString("write_date")
                );
            }

            if (bomLine != null && bomLine.getProduct() != null) {
                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);
                    if (p.getId() == bomLine.getProduct().getId()) {
                        p.addBomLine(bomLine);

                        if(p.getBoms() != null) {
                            for(int j = 0; j < p.getBoms().size(); j++) {
                                Bom b = p.getBoms().get(j);
                                if(bomLine.getBom() != null) {
                                    if(b.getId() == bomLine.getBom().getId()) {
                                        b.addBomLine(bomLine);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return products;
    }
}
