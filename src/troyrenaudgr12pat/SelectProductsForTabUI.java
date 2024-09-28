/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.HashMap;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 * Class for selecting products for a customer tab
 *
 * It displays categories of products and allows adding/removing items to/from
 * the tab.
 */
public class SelectProductsForTabUI extends javax.swing.JFrame {

    private User currentUser;
    private Customer currentCustomer;
    private Tab currentTab;
    private ArrayList<Product> allProducts;
    private HashMap<String, Product> productActionMap = new HashMap<>(); //reference for help with hashmap, https://stackoverflow.com/questions/16819368/hashmap-in-java

    /**
     * Constructor for SelectProductsForTabUI Initializes the UI and sets up the
     * current user and customer. It also creates a new tab and loads the
     * products.
     *
     * @param u The current user
     * @param c The current customer
     */
    public SelectProductsForTabUI(User u, Customer c) {
        initComponents();
        currentUser = u;
        currentCustomer = c;
        createNewTab(c);
        loadProducts();
        populateExistingTabs();
        refreshTabDisplay();
        addTableModelListener();

        /**
         * Adds a focus listener to the tabDisplayTable to ensure that any
         * product notes being edited are saved when the table loses focus. This
         * prevents data loss by stopping the cell editing process before the
         * table loses focus.
         *
         * The focus listener is added as an anonymous inner class of
         * FocusAdapter, and the focusLost method is overridden to handle the
         * specific behavior needed when the table loses focus.
         *
         * If the table is currently being edited when focus is lost, the
         * getCellEditor() method retrieves the active cell editor and calls
         * stopCellEditing() to save the current value being edited.
         *
         * This piece of code adds a focus listener to ensure product notes are
         * saved when the table loses focus
         *
         * @param tabDisplayTable The JTable that displays product information
         * and notes.
         */
        tabDisplayTable.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (tabDisplayTable.isEditing()) { // Check if the table is in the middle of editing
                    tabDisplayTable.getCellEditor().stopCellEditing(); // Stop editing when the table loses focus
                }
            }
        });
    }

    /**
     * Creates a new tab for the customer. This method generates a new tab ID
     * and adds it to the database.
     *
     * @param c The current customer
     */
    private void createNewTab(Customer c) {
        DataHandler dh = new DataHandler();
        int newTabID = dh.generateNewTabID();
        currentTab = new Tab(newTabID, currentCustomer, 0.0, false, false);
        dh.addTab(currentTab);
    }

    /**
     * Loads all available products from the database.
     */
    private void loadProducts() {
        DataHandler dh = new DataHandler();
        allProducts = dh.getAllProducts(); // Retrieve all products
    }

    /**
     * Populates the product panels with buttons for each product. This method
     * goes through each product category and adds buttons to the respective
     * panel.
     */
    private void populateExistingTabs() {
        JPanel[] categoryPanels = {bunsRollsPanel, burgersPanel, pizzasPanel, toastedSandwichesPanel, snacksPanel, otherPanel};
        String[] categories = {"Buns & Rolls", "Burgers", "Pizza", "Toasted Sandwiches", "Snacks", "Other"};

        for (int i = 0; i < categories.length; i++) {
            String category = categories[i]; // Get the current category
            JPanel panel = categoryPanels[i]; // Get the corresponding panel
            panel.setLayout(new GridLayout(0, 1)); // Set a layout that adds buttons vertically

            for (int j = 0; j < allProducts.size(); j++) { // Loop through all products
                Product products = allProducts.get(j);
                if (products.getCategory().equals(category)) { // Check if product belongs to this category
                    JButton productButton = new JButton(products.getProductName() + " - R" + products.getPrice()); // Button with product name and price
                    String actionCommand = "product-" + products.getProductID(); // Ensure a unique action command
                    productButton.setActionCommand(actionCommand);
                    productActionMap.put(actionCommand, products); // Add product to map //reference for productActionMap, ActionListener, actionCommand: https://stackoverflow.com/questions/7253712/java-swing-using-actionmap
                    panel.add(productButton); // Add button to panel

                    // Add an action listener to the button to handle clicks
                    productButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            handleProductButtonAction(e); //Handle button clicks  //Reference to: nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }
                    });
                }
            }
        }

        // Refreshes the UI after adding components to ensure the buttons appear properly
        for (int i = 0; i < categoryPanels.length; i++) {
            JPanel panel = categoryPanels[i];
            panel.revalidate(); // Refresh layout
            panel.repaint(); // Redraw panel
        }
    }

    /**
     * Handles the action when a product button is clicked. It adds the product
     * to the current tab.
     */
    private void handleProductButtonAction(ActionEvent e) {
        String actionCommand = e.getActionCommand(); // Get the action command from the button
        Product product = productActionMap.get(actionCommand); // Get the product linked to the button
        if (product != null) {
            if (currentTab == null) {
                createNewTab(currentCustomer); // Create a new tab if none exists
            }
            addProductToTab(product); // Add product to the tab
        }
    }

    /**
     * Adds a selected product to the current tab.
     *
     * @param p The product to be added to the tab
     */
    private void addProductToTab(Product p) {
        if (currentTab == null) {
            createNewTab(currentCustomer); // Ensure there's a tab before adding products
        }
        currentTab.addProduct(p);
        refreshTabDisplay();
    }

    /**
     * Filters the products based on what the user types in the search box. It
     * finds matching products, clears old product buttons, and shows new ones.
     * Also switches to the correct tab where the product is found.
     */
    private void filterProducts() {
        String query = searchField.getText().toLowerCase();
        JPanel[] categoryPanels = {bunsRollsPanel, burgersPanel, pizzasPanel, toastedSandwichesPanel, snacksPanel, otherPanel};
        String[] categories = {"Buns & Rolls", "Burgers", "Pizza", "Toasted Sandwiches", "Snacks", "Other"};

        boolean foundProduct = false; // Variable that shows if a product was found

        // Clears all product buttons from the panels to refresh them
        for (int i = 0; i < categoryPanels.length; i++) {
            JPanel panel = categoryPanels[i];
            panel.removeAll();
        }

        // Goes through each category and searches for matching products
        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];
            JPanel panel = categoryPanels[i];

            for (int j = 0; j < allProducts.size(); j++) {
                Product product = allProducts.get(j);
                String productName = product.getProductName().toLowerCase();
                if (product.getCategory().equals(category) && productName.contains(query)) { // Check if the product is in this category and matches the search
                    JButton productButton = new JButton(product.getProductName() + " - R" + product.getPrice()); // Create a button with the product name and price
                    String actionCommand = "product-" + product.getProductID(); // Create an action command for the button
                    productButton.setActionCommand(actionCommand); // Set the action command for the button
                    productActionMap.put(actionCommand, product); // Add the product to the action map to link it to the button
                    panel.add(productButton); // Add the button to the panel

                    // Add an action listener to handle button clicks
                    productButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            handleProductButtonAction(e); // Handle the button click event
                        }
                    });

                    foundProduct = true; // Mark that at least one product was found

                    // Switch to the correct tab for the found product
                    this.categoryPanels.setSelectedIndex(i);
                }
            }
        }

        // If no product matches the search, display a message
        if (!foundProduct) {
            JOptionPane.showMessageDialog(bunsRollsPanel, "No products found matching your search.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }

        // Refresh each panel to display the updated list of buttons
        for (int i = 0; i < categoryPanels.length; i++) {
            JPanel panel = categoryPanels[i];
            panel.revalidate(); // Refresh the layout
            panel.repaint(); // Redraw the panel
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        categoryPanels = new javax.swing.JTabbedPane();
        bunsRollsPanel = new javax.swing.JPanel();
        burgersPanel = new javax.swing.JPanel();
        pizzasPanel = new javax.swing.JPanel();
        toastedSandwichesPanel = new javax.swing.JPanel();
        snacksPanel = new javax.swing.JPanel();
        otherPanel = new javax.swing.JPanel();
        currentTabPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabDisplayTable = new javax.swing.JTable();
        btnSubmit = new javax.swing.JButton();
        btnRemoveProduct = new javax.swing.JButton();
        closeTabBtn = new javax.swing.JButton();
        paidBeforeCollectionCB = new javax.swing.JCheckBox();
        spftHelpBtn = new javax.swing.JButton();
        spftImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1315, 835));
        setResizable(false);
        getContentPane().setLayout(null);

        searchField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        getContentPane().add(searchField);
        searchField.setBounds(270, 50, 130, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Search:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(200, 60, 60, 17);

        backBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn);
        backBtn.setBounds(20, 20, 70, 30);

        bunsRollsPanel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout bunsRollsPanelLayout = new javax.swing.GroupLayout(bunsRollsPanel);
        bunsRollsPanel.setLayout(bunsRollsPanelLayout);
        bunsRollsPanelLayout.setHorizontalGroup(
            bunsRollsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        bunsRollsPanelLayout.setVerticalGroup(
            bunsRollsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        categoryPanels.addTab("Buns & Rolls", bunsRollsPanel);

        javax.swing.GroupLayout burgersPanelLayout = new javax.swing.GroupLayout(burgersPanel);
        burgersPanel.setLayout(burgersPanelLayout);
        burgersPanelLayout.setHorizontalGroup(
            burgersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        burgersPanelLayout.setVerticalGroup(
            burgersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        categoryPanels.addTab("Burgers", burgersPanel);

        javax.swing.GroupLayout pizzasPanelLayout = new javax.swing.GroupLayout(pizzasPanel);
        pizzasPanel.setLayout(pizzasPanelLayout);
        pizzasPanelLayout.setHorizontalGroup(
            pizzasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        pizzasPanelLayout.setVerticalGroup(
            pizzasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        categoryPanels.addTab("Pizzas", pizzasPanel);

        javax.swing.GroupLayout toastedSandwichesPanelLayout = new javax.swing.GroupLayout(toastedSandwichesPanel);
        toastedSandwichesPanel.setLayout(toastedSandwichesPanelLayout);
        toastedSandwichesPanelLayout.setHorizontalGroup(
            toastedSandwichesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        toastedSandwichesPanelLayout.setVerticalGroup(
            toastedSandwichesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        categoryPanels.addTab("Toasted Sandwiches", toastedSandwichesPanel);

        javax.swing.GroupLayout snacksPanelLayout = new javax.swing.GroupLayout(snacksPanel);
        snacksPanel.setLayout(snacksPanelLayout);
        snacksPanelLayout.setHorizontalGroup(
            snacksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        snacksPanelLayout.setVerticalGroup(
            snacksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        categoryPanels.addTab("Snacks", snacksPanel);

        javax.swing.GroupLayout otherPanelLayout = new javax.swing.GroupLayout(otherPanel);
        otherPanel.setLayout(otherPanelLayout);
        otherPanelLayout.setHorizontalGroup(
            otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        otherPanelLayout.setVerticalGroup(
            otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        categoryPanels.addTab("Other", otherPanel);

        getContentPane().add(categoryPanels);
        categoryPanels.setBounds(20, 110, 640, 530);

        currentTabPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 4));

        tabDisplayTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Price", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabDisplayTable);
        if (tabDisplayTable.getColumnModel().getColumnCount() > 0) {
            tabDisplayTable.getColumnModel().getColumn(0).setResizable(false);
            tabDisplayTable.getColumnModel().getColumn(1).setResizable(false);
            tabDisplayTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout currentTabPanelLayout = new javax.swing.GroupLayout(currentTabPanel);
        currentTabPanel.setLayout(currentTabPanelLayout);
        currentTabPanelLayout.setHorizontalGroup(
            currentTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentTabPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        currentTabPanelLayout.setVerticalGroup(
            currentTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentTabPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(currentTabPanel);
        currentTabPanel.setBounds(690, 130, 570, 514);

        btnSubmit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSubmit.setText("Submit Products");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubmit);
        btnSubmit.setBounds(1100, 730, 180, 50);

        btnRemoveProduct.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRemoveProduct.setText("Remove Product");
        btnRemoveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveProductActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemoveProduct);
        btnRemoveProduct.setBounds(920, 730, 170, 50);

        closeTabBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        closeTabBtn.setText("Close Tab");
        closeTabBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeTabBtnActionPerformed(evt);
            }
        });
        getContentPane().add(closeTabBtn);
        closeTabBtn.setBounds(740, 730, 170, 50);

        paidBeforeCollectionCB.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        paidBeforeCollectionCB.setText("Customer is paying before collection?");
        paidBeforeCollectionCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidBeforeCollectionCBActionPerformed(evt);
            }
        });
        getContentPane().add(paidBeforeCollectionCB);
        paidBeforeCollectionCB.setBounds(450, 740, 270, 37);

        spftHelpBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        spftHelpBtn.setText("Help");
        spftHelpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spftHelpBtnActionPerformed(evt);
            }
        });
        getContentPane().add(spftHelpBtn);
        spftHelpBtn.setBounds(1220, 20, 60, 30);

        spftImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\troyr\\Dropbox\\School Work\\IT\\TroyRenaudGr12PAT\\Troy_Gr_12_PAT_Images\\alexander-grey-62vi3TG5EDg-unsplash.jpg")); // NOI18N
        spftImage.setMaximumSize(new java.awt.Dimension(1300, 800));
        spftImage.setMinimumSize(new java.awt.Dimension(1315, 835));
        spftImage.setPreferredSize(new java.awt.Dimension(1300, 800));
        getContentPane().add(spftImage);
        spftImage.setBounds(-1300, -340, 2820, 1650);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // This method handles the action when the back button is pressed.
    // It creates a new CustomerSelectionUI window, making it visible and closes the current window.
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
        cs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    // This method handles the action when the remove product button is pressed.
    // It removes the selected product from the tab and updates the table.
    private void btnRemoveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProductActionPerformed
        int selectedRow = tabDisplayTable.getSelectedRow();

        // Get the product to remove and remove it from the tab
        if (selectedRow >= 0 && selectedRow < currentTab.getProducts().size()) {
            Product productToRemove = currentTab.getProducts().get(selectedRow);
            currentTab.removeProduct(productToRemove);

            // Refresh the table display with the updated product list
            refreshTabDisplay();

            JOptionPane.showMessageDialog(this, productToRemove.getProductName() + " removed from the tab.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to remove.");
        }
    }//GEN-LAST:event_btnRemoveProductActionPerformed

    // This method adds a listener to the table model to handle changes in the table.
    // It updates product notes in the tab and database when the "Notes" column is edited.
    private void addTableModelListener() {
        DefaultTableModel model = (DefaultTableModel) tabDisplayTable.getModel();

        tabDisplayTable.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 2) { // Check if the "Notes" column is edited
                String newNote = (String) model.getValueAt(row, column);

                if (row >= 0 && row < currentTab.getProducts().size()) {
                    Product productToUpdate = currentTab.getProducts().get(row);
                    ProductWithNote productWithNote = new ProductWithNote(productToUpdate, newNote);

                    currentTab.getProducts().set(row, productWithNote);

                    DataHandler dh = new DataHandler();
                    dh.updateProductNotes(currentTab.getTabID(), productToUpdate.getProductID(), newNote);
                }
            }
        });
    }

    // This method refreshes the table display to show current products and their notes.
    // It also calculates and shows the total price of all products.
    private void refreshTabDisplay() {
        DefaultTableModel model = (DefaultTableModel) tabDisplayTable.getModel();

        // Remove all rows from the table
        model.setRowCount(0);

        // Create a DecimalFormat formatter for formatting prices with ENGLISH locale format
        DecimalFormat decimalFormat = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH));

        for (int i = 0; i < currentTab.getProducts().size(); i++) {
            Product product = currentTab.getProducts().get(i);
            String note = "";
            if (product instanceof ProductWithNote) {
                note = ((ProductWithNote) product).getNotes();
            }

            // Add a row for each product
            model.addRow(new Object[]{
                product.getProductName(),
                decimalFormat.format(product.getPrice()),
                note
            });
        }

        double totalPrice = 0.0;
        for (int i = 0; i < currentTab.getProducts().size(); i++) {
            Product product = currentTab.getProducts().get(i);
            totalPrice += product.getPrice();
            totalPrice = (Math.round(totalPrice * 100.0) / 100.0);
        }

        // Add a row for the total price
        model.addRow(new Object[]{
            "Total",
            decimalFormat.format(totalPrice),
            ""
        });
    }

    // This method handles the action when the submit button is pressed.
    // It updates product notes, tab status, and saves the tab to the database.
    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        DataHandler dh = new DataHandler();
        if (currentCustomer != null && currentCustomer.getCustomerID() > 0) { //ensures the customer is real and has been added to database before further action
            DefaultTableModel tableModel = (DefaultTableModel) tabDisplayTable.getModel();
            int rowCount = tableModel.getRowCount();

            // Update notes for all products except the total row
            for (int i = 0; i < rowCount - 1; i++) { // Exclude the total row
                String notes = tableModel.getValueAt(i, 2).toString(); // Get the notes from column 2
                if (!notes.equalsIgnoreCase("".trim())) {
                    Product product = currentTab.getProducts().get(i);
                    ProductWithNote updatedProduct = new ProductWithNote(product, notes);
                    currentTab.getProducts().set(i, updatedProduct);
                }
            }

            // Update tab's paid status based on checkbox
            currentTab.setPaid(paidBeforeCollectionCB.isSelected());

            // Save tab
            int rowsAffected = dh.saveTab(currentTab);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Tab saved successfully!");
                refreshTabDisplay(); // Refresh the table to show the updated notes and keep the total row

                CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
                cs.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save tab.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No valid customer selected.");
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    // This method handles the action when the close tab button is pressed.
    // It removes the current tab and navigates back to the customer selection UI.
    private void closeTabBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeTabBtnActionPerformed
        if (currentTab != null) {
            DataHandler dh = new DataHandler();
            dh.removeTab(currentTab);
        }
        CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
        cs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_closeTabBtnActionPerformed

    // This method handles the action when the paid before collection checkbox is clicked.
    // It updates the paid status of the current tab in the database.
    private void paidBeforeCollectionCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidBeforeCollectionCBActionPerformed
        if (currentTab != null) {
            DataHandler dh = new DataHandler();
            boolean isPaid = paidBeforeCollectionCB.isSelected();
            currentTab.setPaid(isPaid);
            dh.updateTabPaidStatus(currentTab.getTabID(), isPaid);
        }
    }//GEN-LAST:event_paidBeforeCollectionCBActionPerformed

    /**
     * Displays a help message dialog to guide users on how to use the Select
     * Products for Tab UI.
     *
     * @param evt action event triggered by clicking the help button
     */
    private void spftHelpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spftHelpBtnActionPerformed
        String helpMessage = "1. This interface allows users to select products to add to a customer tab.\n\n"
                + "2. The product categories displayed include:\n"
                + "   - Buns & Rolls\n"
                + "   - Burgers\n"
                + "   - Pizzas\n"
                + "   - Toasted Sandwiches\n"
                + "   - Snacks\n"
                + "   - Other\n\n"
                + "3. Adding Products:\n"
                + "   - Click on a product button to add it to the current tab.\n"
                + "   - A new tab will be created if one does not exist.\n\n"
                + "4. Viewing Current Tab:\n"
                + "   - The current tab will be displayed on the right side with the products added.\n"
                + "   - You can edit the notes for each product in the table.\n\n"
                + "5. Submitting Products:\n"
                + "   - Click the 'Submit Products' button to save the selected products to the tab.\n"
                + "   - You can also remove products from the tab before submitting.\n\n"
                + "6. Closing the Tab:\n"
                + "   - Use the 'Close Tab' button to finalize the tab after submission.\n\n"
                + "7. Searching for Products:\n"
                + "   - First type any product name into the 'Search' text field."
                + "   - Then click 'enter' to search for the product."
                + "8. Navigation:\n"
                + "   - Click the 'Back' button to return to the previous screen.\n\n"
                + "If you have any further questions, please contact support.";

        JOptionPane.showMessageDialog(null, helpMessage, "Select Products for Tab Help", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_spftHelpBtnActionPerformed

    /**
     * This method is triggered when the user presses enter or performs an
     * action in the search field. It gets the search text and calls the
     * filterProducts() method to filter the products.
     *
     * @param evt The event triggered when the user interacts with the search
     * field
     */
    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        String searchInput = searchField.getText().toLowerCase();
        filterProducts();
    }//GEN-LAST:event_searchFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SelectProductsForTabUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectProductsForTabUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectProductsForTabUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectProductsForTabUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new SelectProductsForTabUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton btnRemoveProduct;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JPanel bunsRollsPanel;
    private javax.swing.JPanel burgersPanel;
    private javax.swing.JTabbedPane categoryPanels;
    private javax.swing.JButton closeTabBtn;
    private javax.swing.JPanel currentTabPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel otherPanel;
    private javax.swing.JCheckBox paidBeforeCollectionCB;
    private javax.swing.JPanel pizzasPanel;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel snacksPanel;
    private javax.swing.JButton spftHelpBtn;
    private javax.swing.JLabel spftImage;
    private javax.swing.JTable tabDisplayTable;
    private javax.swing.JPanel toastedSandwichesPanel;
    // End of variables declaration//GEN-END:variables
}
