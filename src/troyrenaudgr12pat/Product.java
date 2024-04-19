/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

import java.time.LocalDate;

/**
 *
 * @author troyr
 */
public class Product {
    private int productID;
    private String productName;
    private String category;
    private String barcode;
    private double price;
    private int quantity;
    private LocalDate expiryDate;

    public Product(int productID, String productName, String category, String barcode, double price, int quantity, LocalDate expiryDate) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity; 
        this.expiryDate = expiryDate;
    }

    /**
    * Constructor method for the Product class
    * This will only be used to insert new products.
    * @param productName - the name of the product.
    * @param category - the category of the product.
    * @param barcode - the barcode of the product.
    * @param price - the price of the product.
    * @param quantity - the quantity of the product.
    * @param expiryDate - the expiry date of the product.
    */
    public Product(String productName, String category, String barcode, double price, int quantity, LocalDate expiryDate) {
        this.productName = productName;
        this.category = category;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    
    

    @Override
    public String toString() {
        return "Products{" + "productID=" + productID + ", productName=" + productName + ", category=" + category + ", barcode=" + barcode + ", price=" + price + ", quantity=" + quantity + ", expiryDate=" + expiryDate + '}';
    }
    
    
}
