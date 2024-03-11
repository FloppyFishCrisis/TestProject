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
public class Products {
    private int productID;
    private String productName;
    private String category;
    private String barcode;
    private double price;
    private int quantity;
    private LocalDate expiryDate;

    public Products(int productID, String productName, String category, String barcode, double price, int quantity, LocalDate expiryDate) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Products{" + "productID=" + productID + ", productName=" + productName + ", category=" + category + ", barcode=" + barcode + ", price=" + price + ", quantity=" + quantity + ", expiryDate=" + expiryDate + '}';
    }
    
    
}
