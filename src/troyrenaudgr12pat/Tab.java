/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;

/**
 *
 * @author troyr
 */
public class Tab {

    int tabID;
    private Customer customer;
    double balance;
    private ArrayList<Product> products;
    private String notes;
    private boolean paid;

    public Tab(int tabID, Customer customer, double balance, String notes, boolean paid) {
        this.tabID = tabID;
        this.customer = customer;
        this.balance = balance;
        this.products = new ArrayList<>();
        this.notes = notes;
        this.paid = paid;
    }

    public void addProduct(Product product, int quantity) {
        if (products == null) {
            products = new ArrayList<>(); // Ensure products list is initialized
        }
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
        balance += product.getPrice() * quantity;
    }

    public int getProductQuantity(Product product) {
        int quantity = 0;
        for (Product p : products) {
            if (p.equals(product)) {
                quantity++;
            }
        }
        return quantity;
    }

     public void removeProduct(Product p) {
        products.remove(p);
    }
    
    public String getNotes() {
        return notes;
    }

    public boolean isPaid() {
        return paid;
    }

    public double getTotal() {
        return balance;
    }

    public int getTabID() {
        return tabID;
    }

    public int getCustomerID() {
        return customer.getCustomerID();
    }

    public String getCustomerFullname() {
        return customer.getFullname();
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Product> getProducts() {
        return products != null ? products : new ArrayList<>(); // Ensure non-null return
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Tab{" + "tabID=" + tabID + ", customer=" + customer + ", balance=" + balance + ", products=" + products + ", notes=" + notes + ", paid=" + paid + '}';
    }
}
