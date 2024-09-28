/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import javax.swing.JOptionPane;

/**
 * This class is for the Admin to add new products through the UI.
 */
public class AdminNewProductUI extends javax.swing.JFrame {

    private User adminUser;
    private Customer currentCustomer;

    /**
     * This is the AdminNewProductUI class for creating a new product. It takes
     * two parameters: adminUser for user details and c for customer info.
     *
     * @param adminUser the admin who is logged in
     * @param c the customer that will be assigned the product
     */
    public AdminNewProductUI(User adminUser, Customer c) {
        initComponents();
        this.adminUser = adminUser;
        currentCustomer = c;
        populateCategoryComboBox();
    }

    /**
     * Populates the category combo box with product types. Adds preset
     * categories for the user to choose from.
     */
    private void populateCategoryComboBox() {
        String[] categories = {"Buns & Rolls", "Burgers", "Pizzas", "Toasted Sandwiches", "Snacks", "Other"};

        // Add categories to comboBox
        for (int i = 0; i < categories.length; i++) {
            categoryComboBox.addItem(categories[i]);  // Add each category to the combo box
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
        newProductNameTF = new javax.swing.JTextField();
        newPriceTF = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        insertButton1 = new javax.swing.JButton();
        newProductName = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox<>();
        anpHelpBtn = new javax.swing.JButton();
        anpImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1315, 835));
        setPreferredSize(new java.awt.Dimension(1300, 800));
        setResizable(false);
        getContentPane().setLayout(null);

        newCategory.setText("Category:");
        newCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newCategory.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(newCategory);
        newCategory.setBounds(530, 330, 69, 17);

        newBarcode.setText("Barcode:");
        newBarcode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newBarcode.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(newBarcode);
        newBarcode.setBounds(540, 380, 62, 17);

        newPrice.setText("Price:");
        newPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newPrice.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(newPrice);
        newPrice.setBounds(560, 430, 38, 17);
        getContentPane().add(newBarcodeTF);
        newBarcodeTF.setBounds(640, 370, 150, 30);
        getContentPane().add(newProductNameTF);
        newProductNameTF.setBounds(641, 273, 150, 30);
        getContentPane().add(newPriceTF);
        newPriceTF.setBounds(640, 420, 150, 30);

        btnBack.setBackground(new java.awt.Color(204, 204, 255));
        btnBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(20, 20, 70, 30);

        insertButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        insertButton1.setText("Confirm");
        insertButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(insertButton1);
        insertButton1.setBounds(1160, 730, 120, 50);

        newProductName.setText("Product Name:");
        newProductName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newProductName.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(newProductName);
        newProductName.setBounds(500, 280, 110, 17);

        getContentPane().add(categoryComboBox);
        categoryComboBox.setBounds(641, 321, 150, 30);

        anpHelpBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        anpHelpBtn.setText("Help");
        anpHelpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anpHelpBtnActionPerformed(evt);
            }
        });
        getContentPane().add(anpHelpBtn);
        anpHelpBtn.setBounds(1220, 20, 60, 30);

        anpImage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        anpImage.setForeground(java.awt.Color.white);
        anpImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\troyr\\Dropbox\\School Work\\IT\\TroyRenaudGr12PAT\\Troy_Gr_12_PAT_Images\\milad-fakurian-bexwsdM5BCw-unsplash.jpg")); // NOI18N
        getContentPane().add(anpImage);
        anpImage.setBounds(-1140, -160, 2700, 960);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Back button action. Takes the user back to the product management UI.
     *
     * @param evt the event triggered when the button is clicked
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        AdminProductManagementUI pm = new AdminProductManagementUI(adminUser, currentCustomer);
        pm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * Insert button action. Validates the fields and inserts a new product.
     *
     * @param evt the event triggered when the button is clicked
     */
    private void insertButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButton1ActionPerformed
        String pn = newProductNameTF.getText().trim();
        String category = (String) categoryComboBox.getSelectedItem();
        String barcode = newBarcodeTF.getText().trim();
        String priceText = newPriceTF.getText().trim();
        // Extracts text from different text fields and stores it in separate String variables.
        // .trim helps .isEmpty to work by trimming spaces and seeing if values were left out.

        if (pn.isEmpty() || category.isEmpty() || barcode.isEmpty() || priceText.isEmpty()) {
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

        Product newProduct = new Product(pn, category, barcode, price);

//            // Runs the isValidBarcode code and returns a message if false.
//            //the user can create their own barcode therefore there is no need for a validation check
//            // I have left the barcode as a string because it is up to the program user to decide on what their barcode is. They are even able to put text into a barcode generator.
//        if (!isValidBarcode(barcode)) {
//            JOptionPane.showMessageDialog(null, "Invalid barcode format. Please enter a valid barcode.");
//            return;
//        }

        DataHandler dh = new DataHandler();
        int result = dh.insertNewProduct(newProduct);
        
        if (result > 0) { //if a new user was entered correctly the result will be > 0 therefore it can execute this code
            JOptionPane.showMessageDialog(null, "Product successfully added.");
            AdminProductManagementUI apm = new AdminProductManagementUI(adminUser, currentCustomer);
            apm.setVisible(true);
            this.dispose();
            } else { // if user is not entered correctly, this message dialog will be displayed
            JOptionPane.showMessageDialog(null, "Error adding product. Please try again.");
        }

        clearTextFields();
    }//GEN-LAST:event_insertButton1ActionPerformed

    /**
     * Displays a help message dialog to guide users on how to use the Admin New
     * Product UI.
     *
     * @param evt action event triggered by clicking the help button
     */
    private void anpHelpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anpHelpBtnActionPerformed
        String helpMessage = "1. This interface allows the admin to add new products to the system.\n\n"
                + "2. To add a new product:\n"
                + "   - Enter the product name in the 'Product Name' field.\n"
                + "   - Select the appropriate category from the 'Category' dropdown.\n"
                + "   - Enter the product's barcode in the 'Barcode' field.\n"
                + "   - Enter the price of the product in the 'Price' field.\n\n"
                + "3. Once all fields are completed, click the 'Insert' button to add the product to the system.\n\n"
                + "4. If you wish to return to the previous screen, click the 'Back' button.\n\n"
                + "If you need further assistance, please contact support.";

        JOptionPane.showMessageDialog(null, helpMessage, "Admin New Product Help", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_anpHelpBtnActionPerformed

    /**
     * Method to reset all input fields to blank. Clears the text fields and
     * resets the combo box.
     */
    private void clearTextFields() {
        newProductNameTF.setText("");
        categoryComboBox.setSelectedItem(null);
        newBarcodeTF.setText("");
        newPriceTF.setText("");
    }

//    private boolean isValidBarcode(String barcode) {
//        long b = Long.parseLong(barcode);
//        return barcode.length() <= 12;
//        //This method validates the barcode entry to ensure that the barcode is 12 digits or less and it makes sure it is an integer.
//        
//        // return barcode.matches("\\d{12}");
//        // Ensures the barcode is in the correct format and contains no letters.
//        // Reference: https://stackoverflow.com/questions/21426481/what-does-the-regex-d3-d-mean
//    
//        //the user can create their own barcode therefore there is no need for a validation check
//        // I have left the barcode as a string because it is up to the program user to decide on what their barcode is. They are even able to put text into a barcode generator.
//    }

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
    private javax.swing.JButton anpHelpBtn;
    private javax.swing.JLabel anpImage;
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JButton insertButton1;
    private javax.swing.JLabel newBarcode;
    private javax.swing.JTextField newBarcodeTF;
    private javax.swing.JLabel newCategory;
    private javax.swing.JLabel newPrice;
    private javax.swing.JTextField newPriceTF;
    private javax.swing.JLabel newProductName;
    private javax.swing.JTextField newProductNameTF;
    // End of variables declaration//GEN-END:variables
}
