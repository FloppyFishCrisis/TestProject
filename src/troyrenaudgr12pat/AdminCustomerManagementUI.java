/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package troyrenaudgr12pat;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * This class manages customers in a UI. It displays and allows changes to
 * customer data.
 */
public class AdminCustomerManagementUI extends javax.swing.JFrame {

    private ArrayList<Customer> customers = new ArrayList(); //declare and make space in RAM for products
    private int selected; //Initialise 'selected'
    private User adminUser;
    private Customer currentCustomer;

    /**
     * Constructor to create the CustomerManagementUI.
     *
     * @param adminUser the user managing the customers.
     * @param c the current customer selected.
     */
    public AdminCustomerManagementUI(User adminUser, Customer c) {
        initComponents();
        DataHandler dh = new DataHandler();
        this.addTable();
        customers = dh.getAllCustomers();
        this.adminUser = adminUser;
        currentCustomer = c;
    }

    /**
     * Refreshes the customer table with updated data.
     */
    private void refreshTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblCustomers.getModel();
        dtm.setRowCount(0); // Clears the table.

        // Adds each customer to the table.
        for (int i = 0; i < customers.size(); i++) {
            Object[] customerRow = new Object[]{
                customers.get(i).getCustomerID(),
                customers.get(i).getCustomerFirstname(),
                customers.get(i).getCustomerSurname(),
                customers.get(i).getCustomerCellphoneNumber(),
                customers.get(i).getCustomerEmail()
            };
            dtm.addRow(customerRow);
        }
    }

    /**
     * Adds customers to the table when the UI is opened.
     */
    private void addTable() {
        DataHandler dh = new DataHandler();
        customers = dh.getAllCustomers();
        DefaultTableModel dtm = (DefaultTableModel) tblCustomers.getModel();
        tblCustomers.setModel(dtm);
        Object customerRow[] = new Object[5];

        // Populates the table with customer data.
        for (int i = 0; i < customers.size(); i++) {
            customerRow[0] = customers.get(i).getCustomerID();
            customerRow[1] = customers.get(i).getCustomerFirstname();
            customerRow[2] = customers.get(i).getCustomerSurname();
            customerRow[3] = customers.get(i).getCustomerCellphoneNumber();
            customerRow[4] = customers.get(i).getCustomerEmail();
            dtm.addRow(customerRow);
        }
        tblCustomers.setSelectionMode(0);  // Set the selection to the first customer.
        selected = tblCustomers.getSelectedRow();  // Gets the selected row
    }

    /**
     * Updates text fields with selected customer information.
     */
    private void tblCustomersValueChanged() {
        selected = tblCustomers.getSelectedRow();
        if (selected != -1) {
            nameTF.setText(customers.get(selected).getCustomerFirstname());
            surnameTF.setText(customers.get(selected).getCustomerSurname());
            cellphoneNumberTF.setText(customers.get(selected).getCustomerCellphoneNumber());
            emailTF.setText(customers.get(selected).getCustomerEmail());
        }
    }

    /**
     * Checks the input fields for valid values before editing a customer.
     */
    private void checksForEdit() {
        String firstname = nameTF.getText().trim();
        String surname = surnameTF.getText().trim();
        String cellphoneNumber = cellphoneNumberTF.getText().trim();
        String email = emailTF.getText().trim();

        // Check if any field is empty
        if (firstname.isEmpty() || surname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "First name and surname must be filled in.");
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email format. Please enter a valid email address.");
            return;
        }

        // Validate cellphone number format
        if (!isValidCellphoneNumber(cellphoneNumber)) {
            JOptionPane.showMessageDialog(null, "Invalid cellphone number format. Please enter a valid cellphone number.");
            return;
        }
    }

    /**
     * Validates the cellphone number format.
     *
     * @param cellphoneNumber the number to check.
     * @return true if valid, false if not.
     */
    private boolean isValidCellphoneNumber(String cellphoneNumber) {
        return cellphoneNumber.matches("\\d{10}");
        // Example pattern for a 10-digit number, adjust the pattern based on your requirements
    }

    /**
     * Validates the email format.
     *
     * @param email the email to check.
     * @return true if valid, false if not.
     */
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        // Simple email pattern validation, you can enhance it based on your needs
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cellphoneNumberTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();
        nameTF = new javax.swing.JTextField();
        surnameTF = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        AddCustomerBtn = new javax.swing.JButton();
        nameLbl = new javax.swing.JLabel();
        surnameLbl = new javax.swing.JLabel();
        numberLbl = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        emailLbl = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        acmHelpBtn = new javax.swing.JButton();
        acmImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1315, 835));
        setResizable(false);
        getContentPane().setLayout(null);

        cellphoneNumberTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(cellphoneNumberTF);
        cellphoneNumberTF.setBounds(1060, 380, 160, 30);

        tblCustomers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CustomerID", "Name", "Surname", "Cellphone Number", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomers);
        if (tblCustomers.getColumnModel().getColumnCount() > 0) {
            tblCustomers.getColumnModel().getColumn(0).setResizable(false);
            tblCustomers.getColumnModel().getColumn(1).setResizable(false);
            tblCustomers.getColumnModel().getColumn(2).setResizable(false);
            tblCustomers.getColumnModel().getColumn(3).setResizable(false);
            tblCustomers.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(47, 149, 753, 502);

        nameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(nameTF);
        nameTF.setBounds(1060, 280, 162, 30);

        surnameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(surnameTF);
        surnameTF.setBounds(1060, 330, 162, 30);

        backBtn.setBackground(new java.awt.Color(204, 204, 255));
        backBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn);
        backBtn.setBounds(20, 20, 68, 32);

        AddCustomerBtn.setBackground(new java.awt.Color(102, 204, 255));
        AddCustomerBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AddCustomerBtn.setText("Add Customer");
        AddCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCustomerBtnActionPerformed(evt);
            }
        });
        getContentPane().add(AddCustomerBtn);
        AddCustomerBtn.setBounds(980, 730, 160, 50);

        nameLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLbl.setForeground(new java.awt.Color(255, 255, 255));
        nameLbl.setText("Name:");
        getContentPane().add(nameLbl);
        nameLbl.setBounds(950, 290, 50, 17);

        surnameLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        surnameLbl.setForeground(new java.awt.Color(255, 255, 255));
        surnameLbl.setText("Surname:");
        getContentPane().add(surnameLbl);
        surnameLbl.setBounds(930, 340, 67, 17);

        numberLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        numberLbl.setForeground(new java.awt.Color(255, 255, 255));
        numberLbl.setText("Cellphone Number:");
        getContentPane().add(numberLbl);
        numberLbl.setBounds(870, 390, 133, 17);

        deleteBtn.setBackground(new java.awt.Color(255, 102, 102));
        deleteBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        getContentPane().add(deleteBtn);
        deleteBtn.setBounds(840, 730, 120, 50);

        editBtn.setBackground(new java.awt.Color(51, 255, 51));
        editBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        getContentPane().add(editBtn);
        editBtn.setBounds(1160, 730, 110, 50);

        emailLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        emailLbl.setForeground(new java.awt.Color(255, 255, 255));
        emailLbl.setText("Email:");
        getContentPane().add(emailLbl);
        emailLbl.setBounds(960, 440, 41, 17);

        emailTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(emailTF);
        emailTF.setBounds(1060, 430, 160, 30);

        acmHelpBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acmHelpBtn.setText("Help");
        acmHelpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acmHelpBtnActionPerformed(evt);
            }
        });
        getContentPane().add(acmHelpBtn);
        acmHelpBtn.setBounds(1220, 20, 60, 30);

        acmImage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        acmImage.setForeground(java.awt.Color.white);
        acmImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\troyr\\Dropbox\\School Work\\IT\\TroyRenaudGr12PAT\\Troy_Gr_12_PAT_Images\\milad-fakurian-bexwsdM5BCw-unsplash.jpg")); // NOI18N
        getContentPane().add(acmImage);
        acmImage.setBounds(-1140, -160, 2490, 960);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles mouse clicks on the table in order to select a specific row. When
     * a row is clicked by the mouse, it will show up in text fields to then
     * modify.
     *
     * @param evt the mouse event
     */
    private void tblCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomersMouseClicked
        selected = tblCustomers.getSelectedRow();
        this.tblCustomersValueChanged();
    }//GEN-LAST:event_tblCustomersMouseClicked

    /**
     * Opens the Admin Dashboard when the back button is clicked.
     *
     * @param evt the event from clicking the back button.
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        AdminDashboardUI adb = new AdminDashboardUI(adminUser, currentCustomer);
        adb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * Opens the Add New Customer UI when the button is clicked.
     *
     * @param evt the event from clicking the button.
     */
    private void AddCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCustomerBtnActionPerformed
        AdminNewCustomerUI anc = new AdminNewCustomerUI(adminUser, currentCustomer);
        anc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AddCustomerBtnActionPerformed

    /**
     * Deletes a selected customer when the delete button is clicked. It deletes
     * the selected customer from the database and refreshes the UI.
     *
     * @param evt the event from clicking the delete button.
     */
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if (selected != -1) {
            // Ask for confirmation before deleting the product
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?", "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                DataHandler dh = new DataHandler();

                // Delete the selected product from the ArrayList
                Customer customerToDelete = customers.get(selected);
                dh.deleteCustomer(customerToDelete);
                customers.remove(selected);

                // Delete the selected product from the table's data model
                DefaultTableModel dtm = (DefaultTableModel) tblCustomers.getModel();
                dtm.removeRow(selected);

                // Clear the text fields
                nameTF.setText("");
                surnameTF.setText("");
                cellphoneNumberTF.setText("");
                emailTF.setText("");

                selected = -1; // Reset the selected index
            }
        } else {
            JOptionPane.showMessageDialog(null, "No customer selected for deletion.");
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    /**
     * Edits the selected customer's information when the edit button is
     * clicked.
     *
     * @param evt the event from clicking the edit button.
     */
    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        int customerID = customers.get(selected).getCustomerID();
        String name = nameTF.getText().trim();
        String surname = surnameTF.getText().trim();
        String number = cellphoneNumberTF.getText().trim();
        String email = emailTF.getText().trim();
        // This code extracts information entered or selected by the user and assigns it to different variables and trims speces from the text

        checksForEdit(); // Perform validation checks

        // If checksForEdit shows an error, it will return early and not execute the following code
        if (name.isEmpty() || surname.isEmpty() || number.isEmpty() || email.isEmpty()) {
            return;
        }

        DataHandler dh = new DataHandler();

        try {
            dh.updateCustomer(new Customer(customerID, name, surname, number, email)); // Updates the customer in the database.

            //this code sets/updates the altered values so that they are updated immediately after clicking the edit button
            DefaultTableModel dtm = (DefaultTableModel) tblCustomers.getModel();
            dtm.setValueAt(name, selected, 1);
            dtm.setValueAt(surname, selected, 2);
            dtm.setValueAt(number, selected, 3);
            dtm.setValueAt(email, selected, 4);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price or quantity format. Please enter valid numbers.");
        }
        //Reference: https://stackoverflow.com/questions/3179136/jtable-how-to-refresh-table-model-after-insert-delete-or-update-the-data (helped with refreshing table data)
    }//GEN-LAST:event_editBtnActionPerformed

    /**
     * Displays a help message dialog to guide users on how to use the Customer
     * Management UI.
     *
     * @param evt action event triggered by clicking the help button
     */
    private void acmHelpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acmHelpBtnActionPerformed
        String helpMessage = "1. This interface allows admin users to manage customer data.\n\n"
                + "2. The table displays the following information:\n"
                + "   - CustomerID: The unique identifier for each customer.\n"
                + "   - Name: The customer's first name.\n"
                + "   - Surname: The customer's last name.\n"
                + "   - Cellphone Number: The customer's contact number.\n"
                + "   - Email: The customer's email address.\n\n"
                + "3. Managing Customers:\n"
                + "   - You can select a customer from the table to edit or delete their details.\n"
                + "   - To add a new customer, click the 'Add Customer' button and fill in the details.\n"
                + "   - After editing customer details, click 'Edit' to save changes.\n"
                + "   - To remove a customer, click 'Delete'.\n\n"
                + "4. Navigation:\n"
                + "   - Use the 'Back' button to return to the previous screen.\n\n"
                + "If you need further assistance, please contact support.";

        JOptionPane.showMessageDialog(null, helpMessage, "Customer Management Help", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_acmHelpBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AdminCustomerManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminCustomerManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminCustomerManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminCustomerManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new AdminCustomerManagementUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCustomerBtn;
    private javax.swing.JButton acmHelpBtn;
    private javax.swing.JLabel acmImage;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField cellphoneNumberTF;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JTextField emailTF;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JTextField nameTF;
    private javax.swing.JLabel numberLbl;
    private javax.swing.JLabel surnameLbl;
    private javax.swing.JTextField surnameTF;
    private javax.swing.JTable tblCustomers;
    // End of variables declaration//GEN-END:variables
}
