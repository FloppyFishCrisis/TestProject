/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.SQLException;

/**
 *
 * @author troyr
 */
public class DataHandler {

    private ArrayList<Tab> tabs;

    public DataHandler() {
        this.tabs = new ArrayList<>();
    }

    /**
     * This method gets all of the products in the database makes Product
     * objects from data, puts them in the ArrayList and returns the ArrayList.
     *
     * @return ArrayList<Product> - contains all products in the database.
     */
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList();
        try {
            String sql = "SELECT * FROM wyverndb.tblproducts;";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);
            while (rs.next()) {
                int productID = rs.getInt("productid");
                String productName = rs.getString("productname");
                String category = rs.getString("category");
                String barcode = rs.getString("barcode");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                LocalDate expiryDate = rs.getDate("expirydate").toLocalDate();
                Product p = new Product(productID, productName, category, barcode, price, quantity, expiryDate);
                products.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return products;
    }

    /**
     * Inserts a new product into the database and takes a product as a
     * parameter.
     *
     * @param p - the Product object to be inserted.
     * @return - number of rows changed.
     */
    public int insertNewProduct(Product p) {
        int numRows = 0;
        try {
            //INSERT INTO tblProducts(productName, category, barcode, price, quantity, expiryDate) VALUES ("Hot Chips","Hot Meals",2839163627254,25,15,"2024-10-08")
            String sql = "INSERT INTO tblProducts(productName, category, barcode, price, quantity, expiryDate) VALUES (\"" + p.getProductName() + "\",\"" + p.getCategory() + "\",\"" + p.getBarcode() + "\"," + p.getPrice() + "," + p.getQuantity() + ",\"" + p.getExpiryDate() + "\");";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Deletes a product from the database and takes a product as a parameter.
     *
     * @param p - the Product object to be deleted.
     * @return - number of rows changed.
     */
    public int deleteProduct(Product p) {
        int numRows = 0;
        try {
            //DELETE FROM tblProducts WHERE productID = 5
            String sql = "DELETE FROM tblProducts WHERE productID = " + p.getProductID();
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Updates a product in the database and takes a product as a parameter.
     *
     * @param p - the Product object containing updated details.
     * @return - number of rows changed.
     */
    public int updateProduct(Product p) {
        int numRows = 0;
        try {
            //UPDATE tblProducts SET productName = "Kudu Burger", category = "Hot Meals", barcode = "6294701735294", price = 50, quantity = 25, expiryDate = "2024-08-15" WHERE productID = 4
            String sql = "UPDATE tblProducts SET productName = \"" + p.getProductName() + "\", category = \"" + p.getCategory() + "\", barcode = \"" + p.getBarcode() + "\", price = " + p.getPrice() + ", quantity = " + p.getQuantity() + ", expiryDate = \"" + p.getExpiryDate() + "\" WHERE productID = " + p.getProductID() + ";";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * This method gets all of the customers in the database makes Customer
     * objects from data, puts them in the ArrayList and returns the ArrayList.
     *
     * @return ArrayList<Customer> - contains all customers in the database.
     */
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList();
        try {
            String sql = "SELECT * FROM wyverndb.tblcustomers;";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);
            while (rs.next()) {
                int customerID = rs.getInt("customerid");
                String cfn = rs.getString("customerfirstname");
                String csn = rs.getString("customersurname");
                String ccn = rs.getString("customercellphonenumber");
                String ce = rs.getString("customeremail");
                Customer c = new Customer(customerID, cfn, csn, ccn, ce);
                customers.add(c);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return customers;
    }

    public int insertNewCustomer(Customer c) {
        int numRows = 0;
        try {
            //INSERT INTO tblCustomers(customerFirstname, customerSurname, customerCellphoneNumber, customerEmail) VALUES ()
            String sql = "INSERT INTO tblCustomers(customerFirstname, customerSurname, customerCellphoneNumber, customerEmail) VALUES (\"" + c.getCustomerFirstname() + "\",\"" + c.getCustomerSurname() + "\",\"" + c.getCustomerCellphoneNumber() + "\",\"" + c.getCustomerEmail() + "\");";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    public int deleteCustomer(Customer c) {
        int numRows = 0;
        try {
            //DELETE FROM tblProducts WHERE productID = 5
            String sql = "DELETE FROM tblCustomers WHERE customerID = " + c.getCustomerID();
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Updates a customer in the database and takes a customer as a parameter.
     *
     * @param c - the Customer object containing updated details.
     * @return - number of rows changed.
     */
    public int updateCustomer(Customer c) {
        int numRows = 0;
        try {
            String sql = "UPDATE tblCustomers SET firstname = \"" + c.getCustomerFirstname() + "\", surname = \"" + c.getCustomerSurname() + "\", cellphoneNumber = \"" + c.getCustomerCellphoneNumber() + "\", email = \"" + c.getCustomerEmail() + "\" WHERE customerID = " + c.getCustomerID() + ";";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList();
        try {
            String sql = "SELECT * FROM wyverndb.tblUsers";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);
            while (rs.next()) {
                int userID = rs.getInt("userid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                User u = new User(userID, username, password, role);
                users.add(u);
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return users;
    }

    /**
     * Deletes a user from the database and takes a user as a parameter.
     *
     * @param u - the User object to be deleted.
     * @return - number of rows changed.
     */
    public int deleteUser(User u) {
        int numRows = 0;
        try {
            String sql = "DELETE FROM tblUsers WHERE userID = " + u.getUserID();
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Updates a user in the database and takes a user as a parameter.
     *
     * @param u - the User object containing updated details.
     * @return - number of rows changed.
     */
    public int updateUser(User u) {
        int numRows = 0;
        try {
            String sql = "UPDATE tblUsers SET username = \"" + u.getUsername() + "\", password = \"" + u.getPassword() + "\", role = \"" + u.getRole() + "\" WHERE userID = " + u.getUserID() + ";";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    public int insertNewUser(User u) {
        int numRows = 0;
        try {
            String sql = "INSERT INTO tblUsers(username, password, role) VALUES (\"" + u.getUsername() + "\",\"" + u.getPassword() + "\",\"" + u.getRole() + "\");";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    // method to generate new tab IDs - returns the next available ID
    public int generateNewTabID() {
        return getAllTabs().size() + 1;
    }

    public void addTab(Tab tab) {
        tabs.add(tab);
    }

    public int saveTab(Tab tab) {
        int numRows = 0;
        try {
            Connect conn = new Connect();

            String tabSQL = "INSERT INTO tblTabs (tabID, customerID, balance, notes, paid) VALUES ("
                    + tab.getTabID() + ", "
                    + tab.getCustomerID() + ", "
                    + tab.getBalance() + ", '"
                    + tab.getNotes() + "', "
                    + (tab.isPaid() ? 1 : 0) + ")";
            numRows = conn.makeChange(tabSQL);

            for (Product product : tab.getProducts()) {
                String productSQL = "INSERT INTO tblTabProducts (tabID, customerID, productID, balance) VALUES ("
                        + tab.getTabID() + ", "
                        + tab.getCustomerID() + ", "
                        + product.getProductID() + ", "
                        + product.getPrice() + ")";
                numRows += conn.makeChange(productSQL);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    public int removeProductFromTab(Tab tab, Product p) {
        int numRows = 0;
        try {
            // Remove the product from the tab's product list
            tab.removeProduct(p);
            numRows = 1; // Assuming successful removal
        } catch (Exception e) {
            System.err.println(e);
        }
        return numRows;
    }

    public ArrayList<Tab> getAllTabs() {
        ArrayList<Tab> tabs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tblTabs";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);

            while (rs.next()) {
                int tabID = rs.getInt("tabID");
                int customerID = rs.getInt("customerID");
                double balance = rs.getDouble("balance");
                String notes = rs.getString("notes");
                boolean paid = rs.getBoolean("paid");

                Tab tab = new Tab(tabID, new Customer(customerID), balance, notes, paid);
                tabs.add(tab);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return tabs;
    }

    public String getCustomerNameByID(int customerID) {
        String customerName = "";
        try {
            String sql = "SELECT name FROM tblCustomers WHERE customerID = " + customerID;
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);

            if (rs.next()) {
                customerName = rs.getString("name");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return customerName;
    }

    public void updateTabPaidStatus(int tabID, boolean paid) {
        try {
            String sql = "UPDATE tblTabs SET paid = " + paid + " WHERE tabID = " + tabID;
            Connect conn = new Connect();
            conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public int removeTab(Tab tab) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM tbltabs WHERE tabID = " + tab.getTabID();
            Connect conn = new Connect();
            rowsAffected = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return rowsAffected;
    }
}
