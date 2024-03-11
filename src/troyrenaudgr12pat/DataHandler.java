/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author troyr
 */
public class DataHandler {

    public ArrayList<Products> getAllProducts() {
        ArrayList<Products> products = new ArrayList();
        try {
            String sql = "SELECT * FROM wyverndb.tblproducts;";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);
            while (rs.next()) {
                int productID = rs.getInt("productid");
                String productName = rs.getString("productname");
                String category = rs.getString("category");
                String barcode = rs.getString("barcode");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                LocalDate expiryDate = rs.getDate("expirydate").toLocalDate();
                Products p = new Products(productID, productName, category, barcode, price, quantity, expiryDate);
                products.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return products;
    }
}

