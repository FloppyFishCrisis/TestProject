/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.awt.print.Book;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author troyr
 */
public class ModifyProductUI extends javax.swing.JFrame {

    /**
     * Creates new form ModifyProductUI
     */
    public ModifyProductUI() {
        initComponents();
        dh = new DataHandler();  // Create an instance of the BookHandler class.
        this.addTable();//initialises the table
    }

    private void refreshTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblProducts.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < products.size(); i++) {
            Object[] bookRow = new Object[]{
                products.get(i).getProductID(),
                products.get(i).getProductName(),
                products.get(i).getCategory(),
                products.get(i).getBarcode(),
                products.get(i).getPrice(),
                products.get(i).getQuantity(),
                products.get(i).getExpiryDate()
            };
            dtm.addRow(bookRow);
        }
        //this code repopulated the previous data with updated data, by setting the old rows to the newly updated rows
    }

    private void tblProductsValueChanged() {
        selected = tblProducts.getSelectedRow();
        if (selected != -1) {
            productNameTF.setText(products.get(selected).getProductName());
            categoryTF.setText(products.get(selected).getCategory());
            barcodeTF.setText("" + products.get(selected).getBarcode());
            priceTF.setText("" + products.get(selected).getPrice());
            quantityTF.setText("" + products.get(selected).getQuantity());
            ExpiryDate.setDate(products.get(selected).getExpiryDate());
        }
        //helps to enter the selected rows/variable names so that it can be editted or deleted as shown in the text fields
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        barcodeTF = new javax.swing.JTextField();
        priceTF = new javax.swing.JTextField();
        modifyBarcode = new javax.swing.JLabel();
        modifyPrice = new javax.swing.JLabel();
        productNameTF = new javax.swing.JTextField();
        categoryTF = new javax.swing.JTextField();
        modifyProductName = new javax.swing.JLabel();
        modifyCategory = new javax.swing.JLabel();
        modifyDate = new javax.swing.JLabel();
        deleteButton2 = new javax.swing.JButton();
        editButton2 = new javax.swing.JButton();
        expiryDatePicker = new com.github.lgooddatepicker.components.DatePicker();
        helpButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        quantityTF = new javax.swing.JTextField();
        modifyQuantity = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setBackground(new java.awt.Color(204, 204, 255));
        btnBack.setText("←");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        priceTF.setToolTipText("ISBN Format: ISBN-XX XXX-X-XXXXX-XXX-X");

        modifyBarcode.setText("Barcode:");

        modifyPrice.setText("Price:");

        productNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameTFActionPerformed(evt);
            }
        });

        modifyProductName.setText("Product Name:");

        modifyCategory.setText("Category:");

        modifyDate.setText("Expiry Date:");

        deleteButton2.setBackground(new java.awt.Color(153, 153, 255));
        deleteButton2.setText("Delete");
        deleteButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton2ActionPerformed(evt);
            }
        });

        editButton2.setBackground(new java.awt.Color(51, 153, 0));
        editButton2.setText("Edit");
        editButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton2ActionPerformed(evt);
            }
        });

        helpButton1.setBackground(new java.awt.Color(255, 153, 153));
        helpButton1.setText("Help");
        helpButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButton1ActionPerformed(evt);
            }
        });

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "ProductName", "Category", "Barcode", "Price", "Quantity", "ExpiryDate"
            }
        ));
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProducts);

        quantityTF.setToolTipText("ISBN Format: ISBN-XX XXX-X-XXXXX-XXX-X");

        modifyQuantity.setText("Quantity:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(728, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(modifyCategory)
                    .addComponent(modifyPrice)
                    .addComponent(modifyBarcode)
                    .addComponent(modifyProductName)
                    .addComponent(modifyQuantity)
                    .addComponent(modifyDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(expiryDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(870, 870, 870)
                            .addComponent(helpButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(104, 104, 104)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(productNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(categoryTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(barcodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(deleteButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(editButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(quantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(modifyProductName)
                .addGap(18, 18, 18)
                .addComponent(modifyCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(modifyBarcode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modifyPrice)
                .addGap(18, 18, 18)
                .addComponent(modifyQuantity)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expiryDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBack)
                        .addComponent(helpButton1))
                    .addGap(6, 6, 6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(productNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(categoryTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(barcodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(quantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(120, 120, 120)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(deleteButton2)
                                .addComponent(editButton2))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        LandingPageUI lp = new LandingPageUI();
        lp.setVisible(true);
        this.dispose();
        // Handle the action when the "Back" button is clicked.
        // It opens the LandingPageUI and closes the current window.
    }//GEN-LAST:event_btnBackActionPerformed

    private void productNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameTFActionPerformed
        // Handle the action when the "Title" text field is modified.
    }//GEN-LAST:event_productNameTFActionPerformed

    private void deleteButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton2ActionPerformed
        if (selected != -1) {
            // Delete the selected book from the ArrayList
            Book productToDelete = products.get(selected);
            dh.deleteProduct(productToDelete);
            products.remove(selected);

            // Delete the selected book from the table's data model
            DefaultTableModel dtm = (DefaultTableModel) tblProducts.getModel();
            dtm.removeRow(selected);

            // Clear the text fields
            productNameTF.setText("");
            categoryTF.setText("");
            barcodeTF.setText("");
            priceTF.setText("");
            quantityTF.setText("");
            expiryDatePicker.setDate(null);

            selected = -1; // moves through the rows if not for this code it could not account for all the rows
        }
        // Handle the action when the "Delete" button is clicked.
        // It deletes the selected book from the database and refreshes the UI.
    }//GEN-LAST:event_deleteButton2ActionPerformed

    private void editButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton2ActionPerformed
//        int bookID = books.get(selected).getBookID();
//        String titel = productNameTF.getText();
//        String priceText = barcodeTF.getText();
//        String author = categoryTF.getText();
//        String isbn = priceTF.getText();
//        boolean availability = availabilityCheckBox1.isSelected();
//        LocalDate publicationDate = expiryDatePicker.getDate();
//        //this code extracts information entered or selected by the user assigns them to different variables
//
//        // Check if the priceText is a valid number
//        try {
//            double price = Double.parseDouble(priceText);
//            // If the price is a valid number, update the book
//            dh.updateProduct(new Book(bookID, price, author, isbn, titel, availability, publicationDate));
//            DefaultTableModel dtm = (DefaultTableModel) tblProducts.getModel();
//            dtm.setValueAt(productName, selected, 1);
//            dtm.setValueAt(category, selected, 2);
//            dtm.setValueAt(publicationDate, selected, 3);
//            dtm.setValueAt(availability, selected, 4);
//            dtm.setValueAt(price, selected, 5);
//            dtm.setValueAt(isbn, selected, 6);
//            checksForEdit();
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Invalid price format. Please enter a valid number.");
//        }
//        //this code sets/updates the older values so that they are updated immediately after clicking the edit button
//        //makes sure price is is the right format otherwise displays message dialog
//        //Reference: https://stackoverflow.com/questions/3179136/jtable-how-to-refresh-table-model-after-insert-delete-or-update-the-data (helped with refreshing table data)
    }//GEN-LAST:event_editButton2ActionPerformed

    private void helpButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButton1ActionPerformed
        JOptionPane.showMessageDialog(null, "- Edit the values in the blocks or select a book you want to remove" + "\n" + "- Then click Edit / Delete to change or delete a book");
        //creates a message box that helps the user with what the page does
    }//GEN-LAST:event_helpButton1ActionPerformed

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        selected = tblProducts.getSelectedRow();
        this.tblProductsValueChanged();
        //runs the code: when a row is clicked by the mouse, it will show up in text fields to then edit
    }//GEN-LAST:event_tblProductsMouseClicked

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
            java.util.logging.Logger.getLogger(ModifyProductUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyProductUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyProductUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyProductUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifyProductUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcodeTF;
    private javax.swing.JButton btnBack;
    private javax.swing.JTextField categoryTF;
    private javax.swing.JButton deleteButton2;
    private javax.swing.JButton editButton2;
    private com.github.lgooddatepicker.components.DatePicker expiryDatePicker;
    private javax.swing.JButton helpButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel modifyBarcode;
    private javax.swing.JLabel modifyCategory;
    private javax.swing.JLabel modifyDate;
    private javax.swing.JLabel modifyPrice;
    private javax.swing.JLabel modifyProductName;
    private javax.swing.JLabel modifyQuantity;
    private javax.swing.JTextField priceTF;
    private javax.swing.JTextField productNameTF;
    private javax.swing.JTextField quantityTF;
    private javax.swing.JTable tblProducts;
    // End of variables declaration//GEN-END:variables
}