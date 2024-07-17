/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

/**
 *
 * @author troyr
 */
public class Customer {

    int customerID;
    String customerFirstname;
    String customerSurname;
    String customerCellphoneNumber;
    String customerEmail;

    public Customer(int customerID, String customerFirstname, String customerSurname, String customerCellphoneNumber, String customerEmail) {
        this.customerID = customerID;
        this.customerFirstname = customerFirstname;
        this.customerSurname = customerSurname;
        this.customerCellphoneNumber = customerCellphoneNumber;
        this.customerEmail = customerEmail;
    }

    // Constructor accepting only customer ID
    public Customer(int customerID) {
        this.customerID = customerID;
    }

    // Constructor excluding customerID (e.g., for new customers before insertion into DB)
    public Customer(String customerFirstname, String customerSurname, String customerCellphoneNumber, String customerEmail) {
        this.customerFirstname = customerFirstname;
        this.customerSurname = customerSurname;
        this.customerCellphoneNumber = customerCellphoneNumber;
        this.customerEmail = customerEmail;
    }

    public String getFullname() {
        return customerFirstname + " " + customerSurname;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerFirstname() {
        return customerFirstname;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public String getCustomerCellphoneNumber() {
        return customerCellphoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", customerFirstname=" + customerFirstname + ", customerSurname=" + customerSurname + ", customerCellphoneNumber=" + customerCellphoneNumber + ", customerEmail=" + customerEmail + '}';
    }
}
