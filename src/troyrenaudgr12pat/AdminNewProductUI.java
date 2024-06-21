/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author troyr
 */
public class AdminNewProductUI extends javax.swing.JFrame {
    
    private User adminUser;
    private Customer currentCustomer;

    /**
     * Creates new form AdminNewProduct
     */
    public AdminNewProductUI(User adminUser, Customer c) {
        initComponents();
        this.adminUser = adminUser;
        currentCustomer = c;
        populateCategoryComboBox();
    }
    
    private void populateCategoryComboBox() {
        String[] categories = {"Buns & Rolls", "Burgers", "Pizzas", "Toasted Sandwiches", "Snacks"};

        // Add categories to comboBox
        for (String category : categories) {
            categoryComboBox.addItem(category);
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

        newCategory = new javax.swing.JLabel();
        newBarcode = new javax.swing.JLabel();
        newPrice = new javax.swing.JLabel();
        newBarcodeTF = new javax.swing.JTextField();
        newQuantity = new javax.swing.JLabel();
        newProductNameTF = new javax.swing.JTextField();
        newExpiryDate = new javax.swing.JLabel();
        newExpiryDatePicker = new com.github.lgooddatepicker.components.DatePicker();
        newPriceTF = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        newQuantityTF = new javax.swing.JTextField();
        insertButton1 = new javax.swing.JButton();
        newProductName = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        newCategory.setText("Category:");

        newBarcode.setText("Barcode:");

        newPrice.setText("Price:");

        newBarcodeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBarcodeTFActionPerformed(evt);
            }
        });

        newQuantity.setText("Quantity:");

        newProductNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProductNameTFActionPerformed(evt);
            }
        });

        newExpiryDate.setText("Expiry Date:");

        btnBack.setText("←");
        btnBack.setBackground(new java.awt.Color(204, 204, 255));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        newQuantityTF.setToolTipText("ISBN Format: ISBN-XX XXX-X-XXXXX-XXX-X");
        newQuantityTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newQuantityTFActionPerformed(evt);
            }
        });

        insertButton1.setText("Insert");
        insertButton1.setBackground(new java.awt.Color(255, 153, 0));
        insertButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButton1ActionPerformed(evt);
            }
        });

        newProductName.setText("Product Name:");

        categoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(977, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(newExpiryDate)
                            .addComponent(newQuantity)
                            .addComponent(newPrice)
                            .addComponent(newBarcode)
                            .addComponent(newCategory)
                            .addComponent(newProductName))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(newQuantityTF)
                            .addComponent(newExpiryDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newPriceTF)
                            .addComponent(newBarcodeTF)
                            .addComponent(newProductNameTF)
                            .addComponent(categoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnBack)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newProductNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newProductName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newCategory)
                    .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newBarcode)
                    .addComponent(newBarcodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPrice)
                    .addComponent(newPriceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newQuantity)
                    .addComponent(newQuantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newExpiryDate)
                    .addComponent(newExpiryDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(insertButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newBarcodeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBarcodeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newBarcodeTFActionPerformed

    private void newProductNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProductNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newProductNameTFActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        ProductManagementUI pm = new ProductManagementUI(adminUser, currentCustomer);
        pm.setVisible(true);
        this.dispose();
        // Handle the action when the "Back" button is clicked.
        // It opens the LandingPageUI and closes the current window.
    }//GEN-LAST:event_btnBackActionPerformed

    private void newQuantityTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newQuantityTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newQuantityTFActionPerformed

    private void insertButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButton1ActionPerformed
        String pn = newProductNameTF.getText().trim();
        String category = (String) categoryComboBox.getSelectedItem();
        String barcode = newBarcodeTF.getText().trim();
        String priceText = newPriceTF.getText().trim();
        String quantityText = newQuantityTF.getText().trim();
        // Extracts text from different text fields and stores it in separate String variables.
        // .trim helps .isEmpty to work by trimming spaces and seeing if values were left out.

        if (pn.isEmpty() || category.isEmpty() || barcode.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All text fields must be entered.");
            return;
        }
        // Check if any of the text fields are empty.

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price format. Please enter a valid number.");
            return;
            // Ensures that the price entered is in the correct format and displays a message if it isn't.
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity format. Please enter a valid integer.");
            return;
            // Ensures that the quantity entered is in the correct format and displays a message if it isn't.
        }

        LocalDate publicationDate = newExpiryDatePicker.getDate();
        Product newProduct = new Product(pn, category, barcode, price, quantity, publicationDate);
        // Updates the current edited data.

        if (!isValidBarcode(barcode)) {
            JOptionPane.showMessageDialog(null, "Invalid barcode format. Please enter a valid barcode.");
            return;
            // Runs the isValidBarcode code and returns a message if false.
        }

        // Insert the new Book using BookHandler.
        DataHandler dh = new DataHandler();
        dh.insertNewProduct(newProduct);

        clearTextFields(); // Clears the text fields.
    }//GEN-LAST:event_insertButton1ActionPerformed

    private void categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryComboBoxActionPerformed

    private void clearTextFields() {
        newProductNameTF.setText("");
        categoryComboBox.setSelectedItem(null);
        newBarcodeTF.setText("");
        newPriceTF.setText("");
        newQuantityTF.setText("");
        newExpiryDatePicker.setDate(null);
        // Method to reset the values of text fields.
    }

    private boolean isValidBarcode(String barcode) {
        return barcode.matches("\\d{12}");
        // Ensures the barcode is in the correct format and contains no letters.
        // Reference: https://stackoverflow.com/questions/21426481/what-does-the-regex-d3-d-mean
    }

    private void helpButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "- Type values in the blocks" + "\n" + "- Then click Insert to create a product on the system");
        // Creates a message box that helps the user with what the page does.
    }
    
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
            java.util.logging.Logger.getLogger(AdminNewProductUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminNewProductUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminNewProductUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminNewProductUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new AdminNewProductUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JButton insertButton1;
    private javax.swing.JLabel newBarcode;
    private javax.swing.JTextField newBarcodeTF;
    private javax.swing.JLabel newCategory;
    private javax.swing.JLabel newExpiryDate;
    private com.github.lgooddatepicker.components.DatePicker newExpiryDatePicker;
    private javax.swing.JLabel newPrice;
    private javax.swing.JTextField newPriceTF;
    private javax.swing.JLabel newProductName;
    private javax.swing.JTextField newProductNameTF;
    private javax.swing.JLabel newQuantity;
    private javax.swing.JTextField newQuantityTF;
    // End of variables declaration//GEN-END:variables
}