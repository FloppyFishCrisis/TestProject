/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.SQLException;

/**
 *
 * @author troyr
 */
public class DataHandler {

    /**
     * This method gets all of the products in the database 
     * makes Product objects from data, puts them in the arraylist
     * and returns the arraylist.
     * @return arraylist<Product> - contains all products in the database.
     */
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList();
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
                Product p = new Product(productID, productName, category, barcode, price, quantity, expiryDate);
                products.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return products;
    }
    
    /**
    * Inserts a new product into the database and takes a product as a parameter.
    * @param p - the Product object to be inserted. 
    * @return - number of rows changed.
    */
    public int insertNewProduct (Product p){
        int numRows = 0;
         try {
             //INSERT INTO tblProducts(productName, category, barcode, price, quantity, expiryDate) VALUES ("Hot Chips","Hot Meals",2839163627254,25,15,"2024-10-08")
            String sql = "INSERT INTO tblProducts(productName, category, barcode, price, quantity, expiryDate) VALUES (\"" + p.getProductName() + "\",\"" + p.getCategory() + "\",\"" + p.getBarcode() + "\"," + p.getPrice() + "," + p.getQuantity() + ",\"" + p.getExpiryDate() + "\");";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
             System.err.println(e);
        }
         return numRows;
    }
    
    /**
    * Deletes a product from the database and takes a product as a parameter.
    * @param p - the Product object to be deleted.
    * @return - number of rows changed.
    */
    public int deleteProduct(Product p){
        int numRows = 0;
         try {
            //DELETE FROM tblProducts WHERE productID = 5
            String sql = "DELETE FROM tblProducts WHERE productID = " + p.getProductID();
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
             System.err.println(e);
        }
         return numRows;
    }
    
    /**
    * Updates a product in the database and takes a product as a parameter.
    * @param p - the Product object containing updated details.
    * @return - number of rows changed.
    */
    public int updateProduct(Product p){
        int numRows = 0;
         try {
            //UPDATE tblProducts SET productName = "Kudu Burger", category = "Hot Meals", barcode = "6294701735294", price = 50, quantity = 25, expiryDate = "2024-08-15" WHERE productID = 4
            String sql = "UPDATE tblProducts SET productName = \"" + p.getProductName() + "\", category = \"" + p.getCategory() + "\", barcode = \"" + p.getBarcode() + "\", price = " + p.getPrice() + ", quantity = " + p.getQuantity() + ", expiryDate = \"" + p.getExpiryDate() + "\" WHERE productID = " + p.getProductID() + ";";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
             System.err.println(e);
        }
         return numRows;
    }
}

