/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

/**
 * This class represents a product that has an associated note.
 * It extends the Product class to include a note for the product.
 */
public class ProductWithNote extends Product {
    private String note;

    /**
     * Creates a new ProductWithNote with a note and product details.
     *
     * @param inNote the note for the product
     * @param productID the ID of the product
     * @param productName the name of the product
     * @param category the category of the product
     * @param barcode the barcode of the product
     * @param price the price of the product
     */
    public ProductWithNote(String inNote, int productID, String productName, String category, String barcode, double price) {
        super(productID, productName, category, barcode, price);
        this.note = inNote;
    }
    
    /**
     * Creates a new ProductWithNote with a product ID, name, and a note.
     *
     * @param productID the ID of the product
     * @param productName the name of the product
     * @param inNote the note for the product
     */
    public ProductWithNote(int productID, String productName, String inNote){
        super(productID, productName);
        this.note = inNote;
    }

    /**
     * Creates a new ProductWithNote from another Product and adds a note.
     *
     * @param inP the original product
     * @param note the note to add to the product
     */
    public ProductWithNote(Product inP, String note) {
        super(inP.getProductID(), inP.getProductName(), inP.getCategory(), inP.getBarcode(), inP.getPrice()); //uses all information from parameter product and adds a note to it
        this.note = note;
    }
    

    /**
     * Sets a new note for the product.
     *
     * @param inNote the new note for the product
     */
    public void setNote(String inNote) {
        this.note = inNote;
    }

    /**
     * Gets the note of the product.
     *
     * @return the note of the product
     */
    public String getNotes() {
        return note;
    }

    /**
     * Gets the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return super.getPrice();
    }
    
    /**
     * Gets the product ID.
     *
     * @return the ID of the product
     */
    public int getProductID(){
        return super.getProductID();
    }

    @Override
    public String toString() {
        return "ProductWithNote{" + "note=" + note + '}';
    }
}

