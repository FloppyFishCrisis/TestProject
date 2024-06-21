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
    int customerID;
    double balance;
    private ArrayList<Product> products;

    public Tab(int tabID, int customerID, double balance) {
        this.tabID = tabID;
        this.customerID = customerID;
        this.balance = balance;
    }
    
    // New constructor that accepts a Customer object
    public Tab(int tabID, Customer customer, double balance) {
        this.tabID = tabID;
        this.customerID = customer.getCustomerID();
        this.balance = balance;
    }
    
    public void addProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
        balance += product.getPrice() * quantity;
    }

    // Getters for Tab fields
    public int getTabID() {
        return tabID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public double getBalance() {
        return balance;
    }
    
    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Tab{" + "tabID=" + tabID + ", customerID=" + customerID + ", balance=" + balance + '}';
    }
}
