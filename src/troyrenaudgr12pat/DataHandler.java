/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles database operations related to products, customers, and
 * users. It can get, insert, update, and delete products, customers, and users
 * from the database. It also handles tabs and their associated products.
 *
 * @author troyr
 */
public class DataHandler {

    private ArrayList<Tab> tabs;

    /**
     * Constructor for DataHandler class. Initializes the tabs list.
     */
    public DataHandler() {
        this.tabs = new ArrayList<>();
    }

    /**
     * Gets all products from the database. Creates Product objects from the
     * data and adds them to an ArrayList.
     *
     * @return ArrayList<Product> - list of all products.
     */
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList();
        try {
            String sql = "SELECT * FROM wyverndb.tblproducts;";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);
            while (rs.next()) { // loop through ResultSet
                int productID = rs.getInt("productid");
                String productName = rs.getString("productname");
                String category = rs.getString("category");
                String barcode = rs.getString("barcode");
                double price = rs.getDouble("price");
                Product p = new Product(productID, productName, category, barcode, price);
                products.add(p); // Add the product to the list
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return products;
    }

    /**
     * Inserts a new product into the database.
     *
     * @param p - Product object to be inserted.
     * @return - number of rows affected.
     */
    public int insertNewProduct(Product p) {
        int numRows = 0;
        try {
            String sql = "INSERT INTO tblProducts(productName, category, barcode, price) VALUES (\"" + p.getProductName() + "\",\"" + p.getCategory() + "\",\"" + p.getBarcode() + "\"," + p.getPrice() + ");";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Deletes a product from the database.
     *
     * @param p - Product object to be deleted.
     * @return - number of rows affected.
     */
    public int deleteProduct(Product p) {
        int numRows = 0;
        try {
            String sql = "DELETE FROM tblProducts WHERE productID = " + p.getProductID();
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Updates a product in the database.
     *
     * @param p - Product object containing updated details.
     * @return - number of rows affected.
     */
    public int updateProduct(Product p) {
        int numRows = 0;
        try {
            //UPDATE tblProducts SET productName = "Kudu Burger", category = "Hot Meals", barcode = "6294701735294", price = 50, quantity = 25, expiryDate = "2024-08-15" WHERE productID = 4
            String sql = "UPDATE tblProducts SET productName = \"" + p.getProductName() + "\", category = \"" + p.getCategory() + "\", barcode = \"" + p.getBarcode() + "\", price = " + p.getPrice() + " WHERE productID = " + p.getProductID() + ";";
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Gets all customers from the database (in alphabetical order). Creates
     * Customer objects from the data and adds them to an ArrayList.
     *
     * @return ArrayList<Customer> - list of all customers.
     */
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList();
        try {
            String sql = "SELECT * FROM wyverndb.tblcustomers ORDER BY customerfirstname, customersurname DESC;";
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
        } catch (SQLException e) {
            System.err.println(e);
        }
        return customers;
    }

    /**
     * Gets all customers from the database. Creates Customer objects from the
     * data and adds them to an ArrayList.
     *
     * @return ArrayList<Customer> - list of all customers.
     */
    public int insertNewCustomer(Customer c) {
        int numRows = 0;
        int newCustomerID = 1;  // Default to 1 if no customers exist
        try {
            Connect conn = new Connect();

            // First, get the max customer ID from the database
            String maxIdQuery = "SELECT MAX(customerID) AS maxID FROM tblCustomers;";
            ResultSet rs = conn.query(maxIdQuery);

            if (rs.next()) {
                newCustomerID = rs.getInt("maxID") + 1; //My teacher Mr Tweddle assisted me with this line of code
            }

            // Now insert the new customer with the new customer ID
            String sql = "INSERT INTO tblCustomers(customerID, customerFirstname, customerSurname, customerCellphoneNumber, customerEmail) " + "VALUES (" + newCustomerID + ", \"" + c.getCustomerFirstname() + "\", \"" + c.getCustomerSurname() + "\", \"" + c.getCustomerCellphoneNumber() + "\", \"" + c.getCustomerEmail() + "\");";
            numRows = conn.makeChange(sql);

        } catch (SQLException e) {
            System.err.println(e);
        }
        return newCustomerID;
    }

    /**
     * Gets a customer by their ID from the database.
     *
     * @param customerID - ID of the customer to retrieve.
     * @return Customer - Customer object with the details.
     */
    public Customer getCustomerByID(int customerID) {
        Customer customer = null;
        try {
            String sql = "SELECT * FROM tblCustomers WHERE customerID = " + customerID;
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);

            if (rs.next()) {
                String firstname = rs.getString("customerFirstname");
                String surname = rs.getString("customerSurname");
                String cellphoneNumber = rs.getString("customerCellphoneNumber");
                String email = rs.getString("customerEmail");
                customer = new Customer(firstname, surname, cellphoneNumber, email);
                customer.setCustomerID(customerID);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return customer;
    }

    /**
     * Deletes a customer from the database.
     *
     * @param c - Customer object to be deleted.
     * @return - number of rows affected.
     */
    public int deleteCustomer(Customer c) {
        int numRows = 0;
        try {
            String sql = "DELETE FROM tblCustomers WHERE customerID = " + c.getCustomerID();
            Connect conn = new Connect();
            numRows = conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Updates a customer in the database.
     *
     * @param c - Customer object containing updated details.
     * @return - number of rows affected.
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

    /**
     * Gets all users from the database. Creates User objects from the data and
     * adds them to an ArrayList.
     *
     * @return ArrayList<User> - list of all users.
     */
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
     * Deletes a user from the database.
     *
     * @param u - User object to be deleted.
     * @return - number of rows affected.
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
     * Updates a user in the database.
     *
     * @param u - User object containing updated details.
     * @return - number of rows affected.
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

    /**
     * Inserts a new user into the database.
     *
     * @param u - User object to be inserted.
     * @return - number of rows affected.
     */
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

    /**
     * Generates a new tab ID by getting the maximum existing tab ID and adding
     * one.
     *
     * @return int - the next available tab ID.
     */
    public int generateNewTabID() {
        ResultSet rs = null;
        int id = 0;
        try {
            String sql = "SELECT Max(tabID)+1 AS tabID FROM tblTabs";
            Connect conn = new Connect();
            rs = conn.query(sql);
            rs.next();//get first one
            id = rs.getInt("tabID");
        } catch (SQLException e) {
            System.err.println(e);
        }
        return id;
    }

    /**
     * Adds a new tab to the list of tabs.
     *
     * @param tab - The Tab object to add.
     */
    public void addTab(Tab tab) {
        tabs.add(tab);
    }

    /**
     * Saves a tab to the database. Inserts the tab details and its products.
     *
     * @param tab - The Tab object to save.
     * @return int - The number of rows affected.
     */
    public int saveTab(Tab tab) {
        int numRows = 0;
        try {
            Connect conn = new Connect();
            int tabIDToUse = generateNewTabID(); // Get a new tab ID

            // Determine values for `paid` and `collected`
            int paidValue = 0;
            int collectedValue = 0;

            if (tab.isPaid()) {
                paidValue = 1;
            }

            if (tab.isCollected()) {
                collectedValue = 1;
            }

            // SQL query to insert tab details into tblTabs
            String tabSQL = "INSERT INTO tblTabs (tabID, customerID, balance, paid, collected) VALUES (" + tabIDToUse + ", " + tab.getCustomer().getCustomerID() + ", " + tab.getBalance() + ", " + paidValue + ", " + collectedValue + ")";
            numRows = conn.makeChange(tabSQL);

            // Insert each product with notes into tbltabproduct
            for (int i = 0; i < tab.getProducts().size(); i++) {
                Product p = tab.getProducts().get(i);
                if (p instanceof ProductWithNote) {
                    // SQL query to insert a product with notes
                    String productSQL = "INSERT INTO tblTabProducts (tabID, productID, notes) VALUES (" + tabIDToUse + ", " + p.getProductID() + ", '" + ((ProductWithNote) p).getNotes() + "')";
                    numRows += conn.makeChange(productSQL);
                } else {
                    // SQL query to insert a product without notes
                    String productSQL = "INSERT INTO tblTabProducts (tabID, productID) VALUES (" + tabIDToUse + ", " + p.getProductID() + ")";
                    numRows += conn.makeChange(productSQL);
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Removes a product from a tab.
     *
     * @param tab - The Tab object from which to remove the product.
     * @param p - The Product object to remove.
     * @return int - The number of rows affected (1 if successful).
     */
    public int removeProductFromTab(Tab tab, Product p) {
        int numRows = 0;
        try {
            tab.removeProduct(p);
            numRows = 1; // Assuming that a successful removal occurred
        } catch (Exception e) {
            System.err.println(e);
        }
        return numRows;
    }

    /**
     * Gets all tabs from the database and creates Tab objects.
     *
     * @return ArrayList<Tab> - List of all tabs.
     */
    public ArrayList<Tab> getAllTabs() {
        ArrayList<Tab> tabs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tblTabs, tblCustomers WHERE tblcustomers.customerID = tbltabs.customerID ORDER BY tabid";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);

            while (rs.next()) {
                //customer info and creation of customer object
                Customer c = new Customer(rs.getInt("tblcustomers.customerid"), rs.getString("tblcustomers.customerfirstname"), rs.getString("tblcustomers.customersurname"));

                //get tab info
                int tabID = rs.getInt("tabID");
                double balance = rs.getDouble("balance");
                boolean paid = rs.getBoolean("paid");
                boolean collected = rs.getBoolean("collected");

                Tab tab = new Tab(tabID, c, balance, paid, collected);
                tabs.add(tab);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return tabs;
    }

    /**
     * Gets the name of a customer by their ID.
     *
     * @param customerID - The ID of the customer.
     * @return String - The customer's name.
     */
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

    /**
     * Updates the paid status of a tab.
     *
     * @param tabID - The ID of the tab.
     * @param paid - The new paid status.
     */
    public void updateTabPaidStatus(int tabID, boolean paid) {
        try {
            // ? means: If paid is true, the expression will return "TRUE" whereas if paid is false, the expression will return "FALSE".
            String sql = "UPDATE tblTabs SET paid = " + (paid ? "TRUE" : "FALSE") + " WHERE tabID = " + tabID;
            Connect conn = new Connect();
            conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Removes a tab from the database.
     *
     * @param tab - The Tab object to remove.
     * @return int - The number of rows affected.
     */
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

    /**
     * Updates the collected status of a tab.
     *
     * @param tabID - The ID of the tab.
     * @param collected - The new collected status.
     */
    public void updateTabCollectedStatus(int tabID, boolean collected) {
        try {
            String sql = "UPDATE tblTabs SET collected = " + collected + " WHERE tabID = " + tabID;
            Connect conn = new Connect();
            conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Gets all tabs that are not collected yet. This method retrieves tabs from
     * the database where 'collected' is 0. It builds the list of tabs with
     * customer info, tab info, and product info.
     */
    public ArrayList<Tab> getAllUncollectedTabs() {
        ArrayList<Tab> tabs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM wyverndb.tbltabproducts, tblproducts, tbltabs, tblcustomers " + "WHERE wyverndb.tbltabproducts.productID = tblproducts.productID " + "AND tblcustomers.customerID = tbltabs.customerID " + "AND tbltabproducts.tabID = tbltabs.tabID " + "AND collected = 0 ";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);

            while (rs.next()) {
                // Extract customer info
                int customerID = rs.getInt("tblcustomers.customerID");
                String customerFirstname = rs.getString("tblcustomers.customerFirstname");
                String customerSurname = rs.getString("tblcustomers.customerSurname");
                Customer customer = new Customer(customerID, customerFirstname, customerSurname);

                // Extract tab info
                int tabID = rs.getInt("tbltabs.tabID");
                double balance = rs.getDouble("tbltabs.balance");
                boolean paid = rs.getBoolean("tbltabs.paid");
                boolean collected = rs.getBoolean("tbltabs.collected");

                // Extract product info
                int productID = rs.getInt("tbltabproducts.productID");
                String productName = rs.getString("tblproducts.productName");
                String notes = rs.getString("tbltabproducts.notes");

                // Check if the tab already exists in the list
                int pos = -1;
                for (int i = 0; i < tabs.size(); i++) {
                    if (tabs.get(i).getTabID() == tabID) {
                        pos = i;
                        break;
                    }
                }

                if (pos > -1) { // If the tab is found, add the product to it
                    if (notes != null && !notes.isEmpty()) {
                        ProductWithNote productWithNote = new ProductWithNote(productID, productName, notes);
                        tabs.get(pos).addProduct(productWithNote);
                    } else {
                        Product product = new Product(productID, productName);
                        tabs.get(pos).addProduct(product);
                    }
                } else { // If the tab is not found, create a new tab and add the product
                    Tab newTab = new Tab(tabID, customer, balance, paid, collected);

                    if (notes != null && !notes.isEmpty()) {
                        ProductWithNote pwn = new ProductWithNote(productID, productName, notes);
                        newTab.addProduct(pwn);
                    } else {
                        Product product = new Product(productID, productName);
                        newTab.addProduct(product);
                    }

                    tabs.add(newTab);
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return tabs;
    }

    /**
     * Gets all tabs that are collected. This method retrieves tabs from the
     * database where 'collected' is 1. It builds the list of collected tabs
     * with basic info.
     */
    public ArrayList<Tab> getAllCollectedTabs() {
        ArrayList<Tab> tabs = new ArrayList();
        try {
            String sql = "SELECT DISTINCT tblTabs.tabID, customerID, balance, paid, collected FROM tblTabProducts, tblTabs WHERE collected = 1";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);
            while (rs.next()) {
                int tabID = rs.getInt("tabID");
                int customerID = rs.getInt("customerID");
                double balance = rs.getDouble("balance");
                boolean paid = rs.getBoolean("paid");
                boolean collected = rs.getBoolean("collected");
                Tab t = new Tab(tabID, new Customer(customerID), balance, paid, collected);
                tabs.add(t);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return tabs;
    }

    /**
     * Gets all uncollected tabs for kitchen display. This method retrieves tabs
     * that are not ready yet (ready = 0). It builds the list of tabs with
     * customer info, tab info, and product info.
     */
    public ArrayList<Tab> getAllUncollectedTabsForKitchenDisplay() {
        ArrayList<Tab> tabs = new ArrayList<>();
        try {
            String sql = "SELECT * " + "FROM wyverndb.tbltabproducts, tblproducts, tbltabs, tblcustomers " + "WHERE wyverndb.tbltabproducts.productID = tblproducts.productID " + "AND tblcustomers.customerID = tbltabs.customerID " + "AND tbltabproducts.tabID = tbltabs.tabID " + "AND ready = 0";
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);

            while (rs.next()) {
                int customerID = rs.getInt("tblcustomers.customerID");
                String customerFirstname = rs.getString("tblcustomers.customerFirstname");
                String customerSurname = rs.getString("tblcustomers.customerSurname");
                Customer customer = new Customer(customerID, customerFirstname, customerSurname);
                int tabID = rs.getInt("tabID");
                double balance = rs.getDouble("balance");
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                String notes = rs.getString("notes");
                boolean ready = rs.getBoolean("ready");

                Tab existingTab = null;
                for (int i = 0; i < tabs.size(); i++) {
                    if (tabs.get(i).getTabID() == tabID) {
                        existingTab = tabs.get(i);
                        break;
                    }
                }
                 

                if (existingTab != null) { // If the tab is found add the product to the existing tab
                    if (notes != null && !notes.isEmpty()) {
                        ProductWithNote productWithNote = new ProductWithNote(productID, productName, notes);
                        existingTab.addProduct(productWithNote);

                    } else {
                        Product product = new Product(productID, productName);
                        existingTab.addProduct(product);
                    }

                } else { // If the tab is not found, create a new tab
                    Tab newTab = new Tab(tabID, customer, balance);
                    // Add the product to the new tab
                    if (notes != null && !notes.isEmpty()) {
                        ProductWithNote pwn = new ProductWithNote(productID, productName, notes);
                        newTab.addProduct(pwn);
                    } else {
                        Product product = new Product(productID, productName);
                        newTab.addProduct(product);
                    }
                    tabs.add(newTab);

                }

            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return tabs;
    }

    /**
     * Updates the 'ready' status of a tab in the database. This method sets
     * 'ready' to 1 for a given tabID.
     *
     * @param tabID the ID of the tab to update
     * @param ready the new ready status
     */
    public void updateTabReadyStatus(int tabID, boolean ready) {
        try {
            String sql = "UPDATE wyverndb.tbltabproducts SET ready = 1" + " WHERE tabID = " + tabID;
            Connect conn = new Connect();
            conn.makeChange(sql);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Gets the customer's phone number based on the tab ID. This method
     * retrieves the phone number of the customer for the given tabID.
     *
     * @param tabID the ID of the tab to query
     * @return the customer's phone number
     */
    public String getCustomerPhoneNumberByTabID(int tabID) {
        String phoneNumber = null;
        try {
            String sql = "SELECT customerCellphoneNumber, customerFirstname, customerSurname" + " FROM tblcustomers, tbltabs" + " WHERE tbltabs.customerID = tblcustomers.customerID" + " AND tbltabs.tabID = " + tabID;
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);
            if (rs.next()) {
                phoneNumber = rs.getString("customerCellphoneNumber");
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return phoneNumber;
    }

    /**
     * Gets the customer's full name based on the tab ID. This method retrieves
     * the customer's full name for the given tabID.
     *
     * @param tabID the ID of the tab to query
     * @return the customer's full name
     */
    public String getCustomerNameByTabID(int tabID) {
        String customerName = "";
        try {
            String sql = "SELECT customerFirstname, customerSurname" + " FROM tblcustomers, tbltabs" + " WHERE tbltabs.customerID = tblcustomers.customerID" + " AND tbltabs.tabID = " + tabID;
            Connect conn = new Connect();
            ResultSet rs = conn.query(sql);
            if (rs.next()) {
                String customerFN = rs.getString("customerFirstname");
                String customerSN = rs.getString("customerSurname");
                customerName = customerFN + " " + customerSN;
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return customerName;
    }

    /**
     * Updates the notes for a specific product in a tab. This method sets the
     * notes for the specified productID and tabID in the database.
     *
     * @param tabID the ID of the tab containing the product
     * @param productID the ID of the product to update
     * @param notes the new notes to be set for the product
     */
    public void updateProductNotes(int tabID, int productID, String notes) {
        try {
            String sql = "UPDATE tbltabproducts SET notes = '" + notes + "' WHERE productID = " + productID + " AND tabID = " + tabID;
            Connect conn = new Connect();
            conn.makeChange(sql);
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

}
