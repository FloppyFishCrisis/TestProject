/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

/**
 * Class to manage product details, including constructors and getter methods.
 */
public class Product {

    private int productID;
    private String productName;
    private String category;
    private String barcode; // I have left the barcode as a string because it is up to the program user to decide on what their barcode is. They are even able to put text into a barcode generator.
    private double price;

    /**
     * Constructor to create a product with all details, usually from the
     * database.
     *
     * @param productID the unique ID of the product
     * @param productName the name of the product
     * @param category the category of the product
     * @param barcode the barcode of the product
     * @param price the price of the product
     */
    public Product(int productID, String productName, String category, String barcode, double price) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.barcode = barcode;
        this.price = price;
    }

    /**
     * Constructor for a new product without an ID (e.g. before saving to a
     * database). This will only be used when inserting a new product.
     *
     * @param productName the name of the product
     * @param category the category of the product
     * @param barcode the barcode of the product
     * @param price the price of the product
     */
    public Product(String productName, String category, String barcode, double price) {
        this.productName = productName;
        this.category = category;
        this.barcode = barcode;
        this.price = price;
    }

    /**
     * Constructor for creating a product with only productID and productName.
     * This is used for specific cases like displaying product names for orders.
     *
     * @param productID the unique ID of the product
     * @param productName the name of the product
     */
    public Product(int productID, String productName) { //for getAllTabProductsForKitchenDisplay() method in datahandler
        this.productID = productID;
        this.productName = productName;
    }

    /**
     * Gets the unique ID of the product.
     *
     * @return the product ID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Gets the name of the product.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Gets the category of the product.
     *
     * @return the product category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the barcode of the product.
     *
     * @return the product barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Gets the price of the product.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Provides a string representation of the product object. This is useful
     * for debugging or displaying product details.
     *
     * @return a string containing product details
     */
    @Override
    public String toString() {
        return "Products{" + "productID=" + productID + ", productName=" + productName + ", category=" + category + ", barcode=" + barcode + ", price=" + price + '}';
    }
}
