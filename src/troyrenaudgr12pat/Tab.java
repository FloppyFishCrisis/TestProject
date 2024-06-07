/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

/**
 *
 * @author troyr
 */
public class Tab {
    int tabID;
    int customerID;
    double balance;

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

    @Override
    public String toString() {
        return "Tab{" + "tabID=" + tabID + ", customerID=" + customerID + ", balance=" + balance + '}';
    }
}
