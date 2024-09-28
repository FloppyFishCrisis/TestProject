/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

/**
 * Class to manage customer details, including constructors and getters/setters.
 */
public class Customer {

    private int customerID;
    private String customerFirstname;
    private String customerSurname;
    private String customerCellphoneNumber;
    private String customerEmail;

    /**
     * Constructor to create a customer with all details.
     *
     * @param customerID the unique ID of the customer
     * @param customerFirstname the first name of the customer
     * @param customerSurname the last name of the customer
     * @param customerCellphoneNumber the cellphone number of the customer
     * @param customerEmail the email address of the customer
     */
    public Customer(int customerID, String customerFirstname, String customerSurname, String customerCellphoneNumber, String customerEmail) {
        this.customerID = customerID;
        this.customerFirstname = customerFirstname;
        this.customerSurname = customerSurname;
        this.customerCellphoneNumber = customerCellphoneNumber;
        this.customerEmail = customerEmail;
    }
    
    /**
     * Constructor to create a customer with just an ID.
     * Useful when customer details are not yet available.
     *
     * @param customerID the unique ID of the customer
     */
    public Customer(int customerID) {
        this.customerID = customerID;
    }
    
    /**
     * Constructor to create a customer with a name but no other details.
     *
     * @param customerFirstname the first name of the customer
     * @param customerSurname the last name of the customer
     */
    public Customer(int customerID, String customerFirstname, String customerSurname){
        this.customerFirstname = customerFirstname;
        this.customerSurname = customerSurname;
    }

    /**
     * Constructor for a customer without an ID, typically used for new customers.
     * (e.g. for new customers before insertion into DB)
     *
     * @param customerFirstname the first name of the customer
     * @param customerSurname the last name of the customer
     * @param customerCellphoneNumber the cellphone number of the customer
     * @param customerEmail the email address of the customer
     */
    public Customer(String customerFirstname, String customerSurname, String customerCellphoneNumber, String customerEmail) {
        this.customerFirstname = customerFirstname;
        this.customerSurname = customerSurname;
        this.customerCellphoneNumber = customerCellphoneNumber;
        this.customerEmail = customerEmail;
    }

    /**
     * Gets the full name of the customer.
     *
     * @return the customer's first and last name combined
     */
    public String getFullname() {
        return customerFirstname + " " + customerSurname;
    }

    /**
     * Gets the unique ID of the customer.
     *
     * @return the customer's ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Gets the first name of the customer.
     *
     * @return the customer's first name
     */
    public String getCustomerFirstname() {
        return customerFirstname;
    }

    /**
     * Gets the last name of the customer.
     *
     * @return the customer's last name
     */
    public String getCustomerSurname() {
        return customerSurname;
    }

    /**
     * Gets the cellphone number of the customer.
     *
     * @return the customer's cellphone number
     */
    public String getCustomerCellphoneNumber() {
        return customerCellphoneNumber;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return the customer's email address
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the customer's unique ID.
     *
     * @param customerID the unique ID of the customer
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Provides a string representation of the customer object.
     *
     * @return a string with all customer details
     */
    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", customerFirstname=" + customerFirstname + ", customerSurname=" + customerSurname + ", customerCellphoneNumber=" + customerCellphoneNumber + ", customerEmail=" + customerEmail + '}';
    }
}
