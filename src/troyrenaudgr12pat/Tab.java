/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;

/**
 * Represents a tab in the system, which includes customer details, balance,
 * products, and status.
 *
 * @author troyr
 */
public class Tab {

    private int tabID;
    private Customer customer;
    private double balance;
    private String notes;
    private boolean paid, collected;
    private ArrayList<Product> products;
    private ArrayList<Tab> tabs;
    private ArrayList<ProductWithNote> p;

    /**
     * Constructor for updating, selecting, or deleting a tab.
     *
     * @param tabID The unique identifier for the tab.
     * @param customer The customer associated with the tab.
     * @param balance The current balance of the tab.
     * @param paid Indicates if the tab has been paid.
     * @param collected Indicates if the tab has been collected.
     */
    public Tab(int tabID, Customer customer, double balance, boolean paid, boolean collected) {
        this.tabID = tabID;
        this.customer = customer;
        this.balance = balance;
        this.paid = paid;
        this.collected = collected;
    }

    /**
     * Constructor for creating a new tab where the tabID is not yet known
     * (e.g., when inserting into the database).
     *
     * @param customer The customer associated with the tab.
     * @param balance The initial balance of the tab.
     * @param notes Any additional notes for the tab.
     * @param products List of products associated with the tab.
     * @param paid Indicates if the tab has been paid.
     * @param collected Indicates if the tab has been collected.
     */
    public Tab(Customer customer, double balance, String notes, ArrayList<Product> products, boolean paid, boolean collected) {
        this.customer = customer;
        this.balance = balance;
        this.notes = notes;
        this.products = products;
        this.paid = paid;
        this.collected = collected;
    }

    /**
     * Constructor for displaying tab products in the kitchen.
     *
     * @param tabID The unique identifier for the tab.
     * @param customer The customer associated with the tab.
     * @param balance The balance of the tab.
     */
    public Tab(int tabID, Customer customer, double balance) { //constructor for getAllTabProductsForKitchenDisplay()
        this.tabID = tabID;
        this.customer = customer;
        this.balance = balance;
    }

    /**
     * Adds a product to the tab and updates the balance.
     *
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        balance += product.getPrice();
    }

    /**
     * Gets the quantity of a specific product in the tab.
     *
     * @param product The product to check.
     * @return The quantity of the product.
     */
    public int getProductQuantity(ProductWithNote product) {
        int quantity = 0;
        // Traditional for loop with i++
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equals(product)) {
                quantity++;
            }
        }
        return quantity;
    }

    /**
     * Removes a product from the tab.
     *
     * @param p The product to be removed.
     */
    public void removeProduct(Product p) {
        products.remove(p);
    }

    /**
     * Checks if the tab has been collected.
     *
     * @return True if collected, otherwise false.
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * Checks if the tab has been paid.
     *
     * @return True if paid, otherwise false.
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Gets the total balance of the tab.
     *
     * @return The balance of the tab.
     */
    public double getTotal() {
        return balance;
    }

    /**
     * Gets the unique identifier for the tab.
     *
     * @return The tab ID.
     */
    public int getTabID() {
        return tabID;
    }

    /**
     * Gets the full name of the customer associated with the tab.
     *
     * @return The full name of the customer.
     */
    public String getCustomerFullname() {
        return customer.getFullname();
    }

    /**
     * Gets the current balance of the tab.
     *
     * @return The balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the customer associated with the tab.
     *
     * @return The customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the list of products associated with the tab.
     *
     * @return List of products or an empty list if no products are present.
     */
    public ArrayList<Product> getProducts() {
        if (products != null) {
            return products;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Sets the payment status of the tab.
     *
     * @param paid True if the tab is paid, otherwise false.
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * Sets additional notes for the tab.
     *
     * @param notes The notes to be set.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets additional notes for the tab.
     *
     * @return The notes.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Gets the list of products with notes associated with the tab.
     *
     * @return List of products with notes or an empty list if no products are
     * present.
     */
    public ArrayList<ProductWithNote> getProductsWithNotes() {
        if (p != null) {
            return p;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Gets the list of products for a specific tab ID.
     *
     * @param tabID The ID of the tab to get products for.
     * @return List of products for the specified tab or an empty list if no tab
     * is found.
     */
    public ArrayList<Product> getProductsForTab(int tabID) {
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).getTabID() == tabID) {
                return tabs.get(i).getProducts();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Tab{" + "tabID=" + tabID + ", customer=" + customer + ", balance=" + balance + ", notes=" + notes + ", paid=" + paid + ", collected=" + collected + ", products=" + products + ", tabs=" + tabs + ", p=" + p + '}';
    }
}
