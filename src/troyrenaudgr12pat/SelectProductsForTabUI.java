/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.HashMap;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author troyr
 */
public class SelectProductsForTabUI extends javax.swing.JFrame {

    private User currentUser;
    private Customer currentCustomer;
    private Tab currentTab;
    private ArrayList<Product> allProducts;
    private HashMap<String, Product> productActionMap = new HashMap<>(); //helped with hashmap, https://stackoverflow.com/questions/16819368/hashmap-in-java
    private String notes;

    /**
     * Creates new form LandingPage
     */
    public SelectProductsForTabUI(User u, Customer c) {
        initComponents();
        currentUser = u;
        currentCustomer = c;
        createNewTab();
        loadProducts();
        populateExistingTabs();
        updateTabDisplay();
    }

    private void createNewTab() {
        DataHandler dh = new DataHandler();
        int newTabID = dh.generateNewTabID();
        currentTab = new Tab(newTabID, currentCustomer, 0.0, "", false);
        dh.addTab(currentTab);
    }

    private void loadProducts() {
        DataHandler dh = new DataHandler();
        allProducts = dh.getAllProducts(); // Retrieve all products
    }

    private void populateExistingTabs() {
        // Assumes existing JPanels are named accordingly: bunsPanel, burgersPanel, pizzasPanel, etc.
        JPanel[] categoryPanels = {bunsRollsPanel, burgersPanel, pizzasPanel, toastedSandwichesPanel, snacksPanel};
        String[] categories = {"Buns & Rolls", "Burgers", "Pizzas", "Toasted Sandwiches", "Snacks"};

        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];
            JPanel panel = categoryPanels[i];
            panel.setLayout(new GridLayout(0, 1)); // Ensure layout for adding buttons

            for (Product products : allProducts) {
                if (products.getCategory().equals(category)) {
                    JButton productButton = new JButton(products.getProductName() + " - R" + products.getPrice());
                    String actionCommand = "product-" + products.getProductID(); // Ensure unique command
                    productButton.setActionCommand(actionCommand);
                    productActionMap.put(actionCommand, products); //helps with productActionMap, .addActionListener, etc, https://stackoverflow.com/questions/7253712/java-swing-using-actionmap
                    panel.add(productButton);
                    productButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            handleProductButtonAction(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }
                    });
                }
            }
        }

        // Refresh the UI after adding components
        for (JPanel panel : categoryPanels) {
            panel.revalidate();
            panel.repaint();
        }
    }

    private void handleProductButtonAction(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        Product product = productActionMap.get(actionCommand);
        if (product != null) {
            if (currentTab == null) {
                createNewTab(); // Create a new tab if it is null
            }
            addProductToTab(product);
        }
    }

    private void addProductToTab(Product p) {
        if (currentTab == null) {
            createNewTab();
        }
        currentTab.addProduct(p, 1);
        updateTabDisplay();
    }

    private void updateTabDisplay() {
        if (currentTab != null) {
            DefaultTableModel tableModel = (DefaultTableModel) tabDisplayTable.getModel();
            tableModel.setRowCount(0);

            for (Product p : currentTab.getProducts()) {
                Object[] rowData = {p.getProductName(), "R" + (Math.round(p.getPrice() * 100.0) / 100.0), ""};
                tableModel.addRow(rowData);
            }

            Object[] totalRow = {"Total", "R" + currentTab.getTotal(), ""};
            tableModel.addRow(totalRow);

            TableColumnModel columnModel = tabDisplayTable.getColumnModel();
            columnModel.getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()));

            refreshTable();
        }
    }

    private void refreshTable() {
        DefaultTableModel dtm = (DefaultTableModel) tabDisplayTable.getModel();
        dtm.setRowCount(0); // Clear existing rows

        if (currentTab != null) {
            // Add each product as a separate row
            for (Product p : currentTab.getProducts()) {
                Object[] rowData = {p.getProductName(), "R" + (Math.round(p.getPrice() * 100.0) / 100.0), ""};
                dtm.addRow(rowData);
            }

            // Set total row
            Object[] totalRow = {"Total", "R" + currentTab.getTotal(), ""};
            dtm.addRow(totalRow);

            // Ensure column 2 (Notes) remains editable
            TableColumnModel columnModel = tabDisplayTable.getColumnModel();
            columnModel.getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()));
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

        manageTabsBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        productTabs = new javax.swing.JTabbedPane();
        bunsRollsPanel = new javax.swing.JPanel();
        burgersPanel = new javax.swing.JPanel();
        pizzasPanel = new javax.swing.JPanel();
        toastedSandwichesPanel = new javax.swing.JPanel();
        snacksPanel = new javax.swing.JPanel();
        currentTabPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabDisplayTable = new javax.swing.JTable();
        btnSubmit = new javax.swing.JButton();
        btnRemoveProduct = new javax.swing.JButton();
        closeTabBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        manageTabsBtn.setText("Manage Tabs");
        manageTabsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTabsBtnActionPerformed(evt);
            }
        });

        backBtn.setText("←");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bunsRollsPanelLayout = new javax.swing.GroupLayout(bunsRollsPanel);
        bunsRollsPanel.setLayout(bunsRollsPanelLayout);
        bunsRollsPanelLayout.setHorizontalGroup(
            bunsRollsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 993, Short.MAX_VALUE)
        );
        bunsRollsPanelLayout.setVerticalGroup(
            bunsRollsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );

        productTabs.addTab("Buns & Rolls", bunsRollsPanel);

        javax.swing.GroupLayout burgersPanelLayout = new javax.swing.GroupLayout(burgersPanel);
        burgersPanel.setLayout(burgersPanelLayout);
        burgersPanelLayout.setHorizontalGroup(
            burgersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 993, Short.MAX_VALUE)
        );
        burgersPanelLayout.setVerticalGroup(
            burgersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );

        productTabs.addTab("Burgers", burgersPanel);

        javax.swing.GroupLayout pizzasPanelLayout = new javax.swing.GroupLayout(pizzasPanel);
        pizzasPanel.setLayout(pizzasPanelLayout);
        pizzasPanelLayout.setHorizontalGroup(
            pizzasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 993, Short.MAX_VALUE)
        );
        pizzasPanelLayout.setVerticalGroup(
            pizzasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );

        productTabs.addTab("Pizzas", pizzasPanel);

        javax.swing.GroupLayout toastedSandwichesPanelLayout = new javax.swing.GroupLayout(toastedSandwichesPanel);
        toastedSandwichesPanel.setLayout(toastedSandwichesPanelLayout);
        toastedSandwichesPanelLayout.setHorizontalGroup(
            toastedSandwichesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 993, Short.MAX_VALUE)
        );
        toastedSandwichesPanelLayout.setVerticalGroup(
            toastedSandwichesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );

        productTabs.addTab("Toasted Sandwiches", toastedSandwichesPanel);

        javax.swing.GroupLayout snacksPanelLayout = new javax.swing.GroupLayout(snacksPanel);
        snacksPanel.setLayout(snacksPanelLayout);
        snacksPanelLayout.setHorizontalGroup(
            snacksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 993, Short.MAX_VALUE)
        );
        snacksPanelLayout.setVerticalGroup(
            snacksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );

        productTabs.addTab("Snacks", snacksPanel);

        currentTabPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 4));

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
        tabDisplayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabDisplayTableMouseClicked(evt);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        currentTabPanelLayout.setVerticalGroup(
            currentTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentTabPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnSubmit.setText("Submit Products");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnRemoveProduct.setText("Remove Product");
        btnRemoveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveProductActionPerformed(evt);
            }
        });

        closeTabBtn.setText("Close Tab");
        closeTabBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeTabBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backBtn)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeTabBtn)
                        .addGap(58, 58, 58)
                        .addComponent(btnRemoveProduct)
                        .addGap(45, 45, 45)
                        .addComponent(btnSubmit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(productTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(currentTabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(manageTabsBtn))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addGap(8, 8, 8)
                        .addComponent(currentTabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSubmit)
                    .addComponent(btnRemoveProduct)
                    .addComponent(closeTabBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(manageTabsBtn))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manageTabsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTabsBtnActionPerformed
        ManageTabsUI mt = new ManageTabsUI(currentUser, currentCustomer);
        mt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_manageTabsBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
        cs.setVisible(true);
        this.dispose();
        // Handle the action when the "Back" button is clicked.
        // It opens the CustomerSelectionUI and closes the current window.
    }//GEN-LAST:event_backBtnActionPerformed

    private void tabDisplayTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabDisplayTableMouseClicked

    }//GEN-LAST:event_tabDisplayTableMouseClicked

    private void btnRemoveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProductActionPerformed
        int selectedRow = tabDisplayTable.getSelectedRow();
        if (selectedRow >= 0) {
            Product product = currentTab.getProducts().get(selectedRow);
            currentTab.removeProduct(product);
            updateTabDisplay();
//            JOptionPane.showMessageDialog(this, "Product removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to remove.");
        }
    }//GEN-LAST:event_btnRemoveProductActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        DataHandler dh = new DataHandler();
        // Ensure currentCustomer is correctly set and has a valid customer ID
        if (currentCustomer != null && currentCustomer.getCustomerID() > 0) {
            int rowsAffected = dh.saveTab(currentTab);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Tab saved successfully!");
                ManageTabsUI mt = new ManageTabsUI(currentUser, currentCustomer);
                mt.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save tab.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No valid customer selected.");
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void closeTabBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeTabBtnActionPerformed
        if (currentTab != null) {
            DataHandler dh = new DataHandler();
            dh.removeTab(currentTab);
        }
        CustomerSelectionUI cs = new CustomerSelectionUI(currentUser, currentCustomer);
        cs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_closeTabBtnActionPerformed

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
    private javax.swing.JButton closeTabBtn;
    private javax.swing.JPanel currentTabPanel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton manageTabsBtn;
    private javax.swing.JPanel pizzasPanel;
    private javax.swing.JTabbedPane productTabs;
    private javax.swing.JPanel snacksPanel;
    private javax.swing.JTable tabDisplayTable;
    private javax.swing.JPanel toastedSandwichesPanel;
    // End of variables declaration//GEN-END:variables
}
